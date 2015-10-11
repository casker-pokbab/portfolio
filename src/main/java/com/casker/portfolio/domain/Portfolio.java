/*
 * @(#)Portfolio.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import lombok.Data;

/**
 * @author Kanghoon Choi
 */
@Data
public class Portfolio {

	private int portfolioNo;
	private String portfolioName;
	private String url;
	private Date workDate;
}
