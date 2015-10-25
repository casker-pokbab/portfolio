/*
 * @(#)PortfolioSearch.java $version 2015. 10. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Kanghoon Choi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PortfolioSearch extends BaseSearch {
	
	private String portfolioName;
	private String startDate;
	private String endDate;
	private String displayYN;
}