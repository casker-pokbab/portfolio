/*
 * @(#)Contact.java $version 2015. 10. 26.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import lombok.Data;

/**
 * @author Kanghoon Choi
 */
@Data
public class Contact {

	private String name;
	private String senderAddress;
	private String tel;
	private String company;
	private String project;
	private String budget;
	private String message;
}
