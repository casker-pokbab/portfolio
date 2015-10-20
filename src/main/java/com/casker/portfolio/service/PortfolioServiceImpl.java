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

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.mapper.PortfolioMapper;
import com.casker.portfolio.mapper.RecentlyMapper;


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
	public int getPortfolioListCount() {
		return portfolioMapper.selectPortfolioListCount();
	}
	
	@Override
	public List<Portfolio> getPortfolioList(BaseSearch<Portfolio> baseSearch) {
		return portfolioMapper.selectPortfolioList(baseSearch);
	}
	
	@Override
	public void addPortfolio(Portfolio portfolio) {
		saveImageFile(portfolio.getThumbnail(), portfolio.getThumbnailImage());
		saveImageFile(portfolio.getMainImageName(), portfolio.getMainImage());
		saveImageFile(portfolio.getSubImageName1(), portfolio.getSubImage1());
		saveImageFile(portfolio.getSubImageName2(), portfolio.getSubImage2());
		saveImageFile(portfolio.getSubImageName3(), portfolio.getSubImage3());
		
		portfolio.setPortfolioNo(makePortfolioNo());
		portfolioMapper.insertPortfoilo(portfolio);
	}
	
	private long makePortfolioNo() {
		return Long.parseLong(DateFormatUtils.format(new Date(), "yyMMddhhmm"));
	}
	
	/**
	 * @param portfolio
	 */
	private void saveImageFile(String fileName, MultipartFile multipartFile) {
		File file = new File(filePath + File.separator + fileName);
		
		if (file.isDirectory()) {
			return;
		}
		
		try {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Unable to save image", e);
		}
	}

	@Override
	public void removePortfolio(Portfolio portfolio) {
		portfolioMapper.deletePortfolio(portfolio);
	}
	
	@Override
	public int getRecentlyListCount() {
		return recentlyMapper.selectRecentlyListCount();
	}

	@Override
	public List<Recently> getRecentlyList(BaseSearch<Recently> baseSearch) {
		return recentlyMapper.selectRecentlyList(baseSearch);
	}
	
	@Override
	public void removeRecently(Recently recently) {
		recentlyMapper.deleteRecently(recently);
	}

	@Override
	public Portfolio getPortfolioDetail(int portfolioNo) {
		return portfolioMapper.selectPortfolio(portfolioNo);
	}

	@Override
	public void addRecently(Recently recently) {
		recently.setRecentlyNo(getRecentlyListCount() + 1); //TODO 채번방식 변경필요
		recentlyMapper.insertRecently(recently);
	}


}
