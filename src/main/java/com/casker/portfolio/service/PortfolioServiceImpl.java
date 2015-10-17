/*
 * @(#)PortfolioServiceImple.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
