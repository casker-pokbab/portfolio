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

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.mapper.PortfolioMapper;


/**
 * @author Kanghoon Choi
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Override
	public List<Portfolio> getPortfolioList() {
		return portfolioMapper.selectPortfolioList();
	}

}
