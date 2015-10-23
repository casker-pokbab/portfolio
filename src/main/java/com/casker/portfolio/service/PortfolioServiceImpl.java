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
import java.util.List;

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


/**
 * @author Kanghoon Choi
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {

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
		return Long.parseLong(DateFormatUtils.format(new Date(), "yyMMddhhmm"));
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
}
