/*
 * @(#)PortfolioService.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.util.List;

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Recently;


/**
 * @author Kanghoon Choi
 */
public interface PortfolioService {

	/**
	 * 포트폴리오 리스트를 가져온다.
	 * @param baseSearch 
	 */
	List<Portfolio> getPortfolioList(BaseSearch<Portfolio> baseSearch);
	
	/**
	 * @return
	 */
	int getPortfolioListCount();

	/**
	 * 최근작업 리스트를 가져온다.
	 * @param baseSearch
	 * @return
	 */
	List<Recently> getRecentlyList(BaseSearch<Recently> baseSearch);

	/**
	 * @return
	 */
	int getRecentlyListCount();

	/**
	 * 포트폴리오 상세정보를 가져온다.
	 * 
	 * @param portfolioNo
	 * @return
	 */
	Portfolio getPortfolioDetail(int portfolioNo);

	/**
	 * @param recently
	 */
	void addRecently(Recently recently);

}
