/*
 * @(#)PortfolioServiceImple.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.casker.portfolio.domain.Image;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;
import com.casker.portfolio.mapper.ImageMapper;
import com.casker.portfolio.mapper.PortfolioMapper;
import com.casker.portfolio.mapper.RecentlyMapper;
import com.casker.portfolio.type.ImageType;
import com.casker.portfolio.type.SortType;


/**
 * @author Kanghoon Choi
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {
	private final Map<String, Integer> sortParameter = new HashMap<String, Integer>();

	@Value("#{common['file.path']}")
	private String filePath;
	
	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Autowired
	private RecentlyMapper recentlyMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public int getPortfolioListCount(PortfolioSearch search) {
		return portfolioMapper.selectPortfolioListCount(search);
	}
	
	@Override
	public List<Portfolio> getPortfolioList(PortfolioSearch search) {
		return portfolioMapper.selectPortfolioList(search);
	}
	
	@Override
	public Portfolio getPortfolioDetail(long portfolioNo) {
		Portfolio portfolio = portfolioMapper.selectPortfolio(portfolioNo);
		
		Image imageSearch = new Image();
		imageSearch.setPortfolioNo(portfolioNo);
		List<Image> imageList = imageMapper.selectImageList(imageSearch);
		
		List<Image> subImageList = new ArrayList<Image>();
		for (Image image : imageList) {
			switch (image.getImageType()) {
				case THUMBNAIL:
					portfolio.setThumbnailImage(image);
					break;
					
				case MAIN:
					portfolio.setMainImage(image);
					break;
									
				case SUB:
					subImageList.add(image);
					break;
			}
		}
		portfolio.setSubImageList(subImageList);
		
		return portfolio;
	}
	
	@Override
	public void addPortfolio(Portfolio portfolio) {
		portfolio.setPortfolioNo(makeNo());
		portfolio.setSort(portfolioMapper.selectPortfolioListCount(null) + 1);
		
		int seq = 0;
		saveImageFile(portfolio.getThumbnailImage(), portfolio.getPortfolioNo(), seq++);
		saveImageFile(portfolio.getMainImage(), portfolio.getPortfolioNo(), seq++);
		for (Image subImage : portfolio.getSubImageList()) {
			saveImageFile(subImage, portfolio.getPortfolioNo(), seq++);
		}
		
		portfolioMapper.insertPortfoilo(portfolio);
	}
	
	private long makeNo() {
		return Long.parseLong(DateFormatUtils.format(new Date(), "yyMMddhhmmss"));
	}
	
	/**
	 * @param portfolio
	 */
	private void saveImageFile(Image image, long portfolioNo, int seq) {
		if (image.getFile().isEmpty()) {
			return;
		}
		String imageId = String.valueOf(portfolioNo) + seq;
		String fileName = "casker_portfolio_" + imageId + "." + StringUtils.substringAfterLast(image.getRealFileName(), ".");
		
		File file = new File(filePath + File.separator + image.getImageType().getFilePath() + File.separator + fileName);
		MultipartFile multipartFile = image.getFile();
		
		if (multipartFile == null || file.isDirectory()) {
			return;
		}
		
		try {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Unable to save image", e);
		}
		
		image.setImageId(imageId);
		image.setPortfolioNo(portfolioNo);
		image.setFileName(fileName);
		imageMapper.insertImage(image);
	}
	
	@Override
	public void editPortfolio(Portfolio portfolio) {
		Portfolio oldPortfolio = getPortfolioDetail(portfolio.getPortfolioNo());
		
		int seq = getNextImageSeq(portfolio.getPortfolioNo());
		editImageFile(portfolio.getThumbnailImage(), portfolio.getPortfolioNo(), oldPortfolio.getThumbnailImage(), seq++);
		editImageFile(portfolio.getMainImage(), portfolio.getPortfolioNo(), oldPortfolio.getMainImage(), seq++);
		if (CollectionUtils.isNotEmpty(portfolio.getSubImageList())) {
			for (int index = 0; index < portfolio.getSubImageList().size(); index++) {
				Image oldSubImage = null;
				if (index < oldPortfolio.getSubImageList().size() - 1) {
					oldSubImage = oldPortfolio.getSubImageList().get(index);
				}
				editImageFile(portfolio.getSubImageList().get(index), portfolio.getPortfolioNo(), oldSubImage, seq++);
			}
		}
		
		portfolioMapper.updatePortfolio(portfolio);
	}
	
	private int getNextImageSeq(long portfolioNo) {
		String maxImageId = imageMapper.selectMaxImageId(portfolioNo);
		return Integer.parseInt(maxImageId.replace(String.valueOf(portfolioNo), "")) + 1;
	}
	
	private void editImageFile(Image image, long portfolioNo, Image oldImage, int seq) {
		if (image.getFile().isEmpty()) {
			return;
		}
		removeImageFile(oldImage);
		saveImageFile(image, portfolioNo, seq);
	}
	
	private void removeImageFile(Image oldImage) {
		if (oldImage == null) {
			return;
		}
		
		File file = new File(filePath + File.separator + oldImage.getFileName());
		if(file.exists()){
			file.delete();
		}
		imageMapper.deleteImage(oldImage);
	}
	
	@Override
	public File getImageFile(long portfolioNo, String imageTypeCode, int seq) {
		ImageType imageType = ImageType.valueOfCode(imageTypeCode);
		Image imageSearch = new Image();
		imageSearch.setPortfolioNo(portfolioNo);
		imageSearch.setImageType(imageType);
		
		List<Image> imageList = imageMapper.selectImageList(imageSearch);
		Image image = imageList.get(seq);

		return new File(filePath + File.separator + imageType.getFilePath() + File.separator + image.getFileName());
	}

	@Override
	public void removePortfolio(Portfolio portfolio) {
		portfolioMapper.deletePortfolio(portfolio);
	}
	
	@Override
	public void adjustPortfolioSort(int sort, SortType sortType) {
		int totalCount = portfolioMapper.selectPortfolioListCount(new PortfolioSearch());
		
		if (SortType.HIGH == sortType && sort < totalCount) {
			updatePortfolioSort(sort, 0);
			updatePortfolioSort(sort + 1, sort);
			updatePortfolioSort(0, sort + 1);
		} else if (SortType.LOW == sortType && sort > 1) {
			updatePortfolioSort(sort, 0);
			updatePortfolioSort(sort - 1, sort);
			updatePortfolioSort(0, sort - 1);
		}
	}
	
	private void updatePortfolioSort(int oldSort, int newSort) {
		sortParameter.put("oldSort", oldSort);
		sortParameter.put("newSort", newSort);
		portfolioMapper.updatePortfolioSort(sortParameter);
	}
	
	@Override
	public int getRecentlyListCount(RecentlySearch search) {
		return recentlyMapper.selectRecentlyListCount(search);
	}

	@Override
	public List<Recently> getRecentlyList(RecentlySearch search) {
		return recentlyMapper.selectRecentlyList(search);
	}
	
	@Override
	public Recently getRecentlyDetail(long recentlyNo) {
		return recentlyMapper.selectRecently(recentlyNo);
	}
	
	@Override
	public void addRecently(Recently recently) {
		recently.setRecentlyNo(makeNo());
		recently.setSort(recentlyMapper.selectRecentlyListCount(null) + 1);
		recentlyMapper.insertRecently(recently);
	}

	@Override
	public void editRecently(Recently recently) {
		recentlyMapper.updateRecently(recently);
	}

	@Override
	public void removeRecently(Recently recently) {
		recentlyMapper.deleteRecently(recently);
	}

	@Override
	public void adjustRecentlySort(int sort, SortType sortType) {
		int totalCount = recentlyMapper.selectRecentlyListCount(new RecentlySearch());
		
		if (SortType.HIGH == sortType && sort < totalCount) {
			updateRecentlySort(sort, 0);
			updateRecentlySort(sort + 1, sort);
			updateRecentlySort(0, sort + 1);
		} else if (SortType.LOW == sortType && sort > 1) {
			updateRecentlySort(sort, 0);
			updateRecentlySort(sort - 1, sort);
			updateRecentlySort(0, sort - 1);
		}
	}
	
	private void updateRecentlySort(int oldSort, int newSort) {
		sortParameter.put("oldSort", oldSort);
		sortParameter.put("newSort", newSort);
		recentlyMapper.updateRecentlySort(sortParameter);
	}
}
