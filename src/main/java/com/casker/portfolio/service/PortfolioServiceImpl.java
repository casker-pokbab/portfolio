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
import com.casker.portfolio.domain.Project;
import com.casker.portfolio.mapper.PortfolioMapper;
import com.casker.portfolio.mapper.ProjectMapper;


/**
 * @author Kanghoon Choi
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	private PortfolioMapper portfolioMapper;
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public List<Portfolio> getPortfolioList(BaseSearch baseSearch) {
		return portfolioMapper.selectPortfolioList(baseSearch);
	}
	
	@Override
	public int getProjectListCount() {
		return projectMapper.selectProjectListCount();
	}

	@Override
	public List<Project> getProjectList(BaseSearch baseSearch) {
		return projectMapper.selectProjectList(baseSearch);
	}

	@Override
	public Portfolio getPortfolioDetail(int portfolioNo) {
		return portfolioMapper.selectPortfolio(portfolioNo);
	}
}
