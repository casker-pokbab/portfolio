/*
 * @(#)User.java $version 2015. 10. 15.
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
public class User {

	private String id;
	private String password;
	private String name;
	private Date createDate;
	private Date updateDate;
}
