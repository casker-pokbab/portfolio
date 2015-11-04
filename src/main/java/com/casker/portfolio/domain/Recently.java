/*
 * @(#)Project.java $version 2015. 9. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 프로젝트
 * 
 * @author Kanghoon Choi
 */
@Data
public class Recently {

	private long recentlyNo;

	private String recentlyName;

	private String companyName;

	private String participationPart;

	private Date createDate;

	private String url;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	private String displayYN;

	private int sort;
}
