/*
 * @(#)PortfolioMapper.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import java.util.List;

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;


/**
 * @author Kanghoon Choi
 */
public interface PortfolioMapper {

	/**
	 * @param search 
	 * @return
	 */
	List<Portfolio> selectPortfolioList(PortfolioSearch search);
	
	/**
	 * @param portfolioNo
	 * @return
	 */
	Portfolio selectPortfolio(long portfolioNo);
	
	/**
	 * @return
	 */
	int selectPortfolioListCount(PortfolioSearch search);

	/**
	 * @param portfolio
	 */
	void insertPortfoilo(Portfolio portfolio);

	/**
	 * @param portfolio
	 */
	void updatePortfolio(Portfolio portfolio);
	

	/**
	 * @param portfolio
	 */
	void deletePortfolio(Portfolio portfolio);
}
