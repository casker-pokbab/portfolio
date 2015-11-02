/*
 * @(#)RecentlySearch.java $version 2015. 10. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Kanghoon Choi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RecentlySearch extends BaseSearch {

	private String recentlyName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private String companyName;
	
	private String participationPart;
	
	private String displayYN;
}
