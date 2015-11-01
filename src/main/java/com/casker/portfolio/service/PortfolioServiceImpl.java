/*
 * @(#)PortfolioServiceImple.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;
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
		return portfolioMapper.selectPortfolio(portfolioNo);
	}
	
	@Override
	public void addPortfolio(Portfolio portfolio) {
		saveImageFile(portfolio.getThumbnail(), portfolio.getThumbnailImage());
		saveImageFile(portfolio.getMainImageName(), portfolio.getMainImage());
		saveImageFile(portfolio.getSubImageName1(), portfolio.getSubImage1());
		saveImageFile(portfolio.getSubImageName2(), portfolio.getSubImage2());
		saveImageFile(portfolio.getSubImageName3(), portfolio.getSubImage3());
		
		portfolio.setPortfolioNo(makeNo());
		portfolio.setSort(portfolioMapper.selectPortfolioListCount(null) + 1);
		portfolioMapper.insertPortfoilo(portfolio);
	}
	
	private long makeNo() {
		return Long.parseLong(DateFormatUtils.format(new Date(), "yyMMddhhmmss"));
	}
	
	/**
	 * @param portfolio
	 */
	private void saveImageFile(String fileName, MultipartFile multipartFile) {
		File file = new File(filePath + File.separator + fileName);
		
		if (multipartFile == null || file.isDirectory()) {
			return;
		}
		
		try {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Unable to save image", e);
		}
	}
	
	@Override
	public void editPortfolio(Portfolio portfolio) {
		Portfolio oldPortfolio = portfolioMapper.selectPortfolio(portfolio.getPortfolioNo());
		editImageFile(portfolio.getThumbnail(), portfolio.getThumbnailImage(), oldPortfolio.getThumbnail());
		editImageFile(portfolio.getMainImageName(), portfolio.getMainImage(), oldPortfolio.getMainImageName());
		editImageFile(portfolio.getSubImageName1(), portfolio.getSubImage1(), oldPortfolio.getSubImageName1());
		editImageFile(portfolio.getSubImageName2(), portfolio.getSubImage2(), oldPortfolio.getSubImageName2());
		editImageFile(portfolio.getSubImageName3(), portfolio.getSubImage3(), oldPortfolio.getSubImageName3());
		
		portfolioMapper.updatePortfolio(portfolio);
	}
	
	private void editImageFile(String fileName, MultipartFile multipartFile, String oldFileName) {
		if (multipartFile == null) {
			return;
		}
		removeImageFile(oldFileName);
		saveImageFile(fileName, multipartFile);
	}
	
	private void removeImageFile(String oldFileName) {
		File file = new File(filePath + File.separator + oldFileName);
		if(file.exists()){
			file.delete();
		}
	}
	
	@Override
	public File getImageFile(Portfolio portfolio, String imageType) {
		ImageType type = ImageType.valueOfCode(imageType);
		
		return new File(filePath + File.separator + type.getFilePath(portfolio));
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
