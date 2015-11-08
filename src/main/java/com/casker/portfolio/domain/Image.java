/*
 * @(#)Image.java $version 2015. 11. 4.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.casker.portfolio.type.ImageType;

import lombok.Data;


/**
 * @author Kanghoon Choi
 */
@Data
public class Image {
	
	private String imageId;
	
	private long portfolioNo;
	
	private ImageType imageType;
	
	private String realFileName;
	
	private String fileName;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private MultipartFile file;
	
	public void setFile(MultipartFile file) {
		this.file = file;
		this.realFileName = file.getOriginalFilename();
	}
}
