/*
 * @(#)PortfolioService.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.io.File;
import java.util.List;

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;
import com.casker.portfolio.type.SortType;


/**
 * @author Kanghoon Choi
 */
public interface PortfolioService {

	/**
	 * 포트폴리오 리스트를 가져온다.
	 * @param search 
	 */
	List<Portfolio> getPortfolioList(PortfolioSearch search);
	
	/**
	 * @param search 
	 * @return
	 */
	int getPortfolioListCount(PortfolioSearch search);

	/**
	 * @param portfolio
	 */
	void addPortfolio(Portfolio portfolio);

	/**
	 * 포트폴리오 상세정보를 가져온다.
	 * 
	 * @param portfolioNo
	 * @return
	 */
	Portfolio getPortfolioDetail(long portfolioNo);

	/**
	 * @param portfolio
	 */
	void editPortfolio(Portfolio portfolio);

	/**
	 * @param portfolio
	 */
	void removePortfolio(Portfolio portfolio);

	/**
	 * 최근작업 리스트를 가져온다.
	 * @param search
	 * @return
	 */
	List<Recently> getRecentlyList(RecentlySearch search);

	/**
	 * @return
	 */
	int getRecentlyListCount(RecentlySearch search);

	/**
	 * @param recentlyNo
	 * @return
	 */
	Recently getRecentlyDetail(long recentlyNo);
	
	/**
	 * @param recently
	 */
	void addRecently(Recently recently);

	/**
	 * @param recently
	 */
	void editRecently(Recently recently);

	/**
	 * @param recently
	 */
	void removeRecently(Recently recently);

	/**
	 * @param portfolio
	 * @param imageType
	 * @return
	 */
	File getImageFile(Portfolio portfolio, String imageType);

	/**
	 * @param sort
	 * @param sortType
	 */
	void adjustSort(int sort, SortType sortType);
	

}
