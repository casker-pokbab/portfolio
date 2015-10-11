/*
 * @(#)PortfolioService.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import java.util.List;

import com.casker.portfolio.domain.Portfolio;


/**
 * @author Kanghoon Choi
 */
public interface PortfolioService {

	/**
	 * 포트폴리오 리스트를 가져온다.
	 * 
	 * @return
	 */
	List<Portfolio> getPortfolioList();

}
