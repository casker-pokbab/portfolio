/*
 * @(#)Project.java $version 2015. 9. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import lombok.Data;

/**
 * 프로젝트
 * 
 * @author Kanghoon Choi
 */
@Data
public class Project {

	private int projectNo;
	private String projectName;
	private String participationPart;
	private Date date;
	private String url;
}
