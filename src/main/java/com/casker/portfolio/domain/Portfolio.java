/*
 * @(#)Portfolio.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author Kanghoon Choi
 */
@Data
public class Portfolio {

	private long portfolioNo;
	private String portfolioName;
	private String startDate;
	private String endDate;
	private String thumbnail;
	private MultipartFile thumbnailImage;
	private String url;
	private String mainImageName;
	private MultipartFile mainImage;
	private String title;
	private String description;
	private String info;
	private String participant;
	private String subImageName1;
	private MultipartFile subImage1;
	private String subImageName2;
	private MultipartFile subImage2;
	private String subImageName3;
	private MultipartFile subImage3;
	private String viewName;
	private Date workDate;
	private String displayYN;
	
	public void setThumbnailImage(MultipartFile thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
		this.thumbnail = "/thumbnail/" + thumbnailImage.getOriginalFilename();
	}
	
	public void setMainImage(MultipartFile mainImage) {
		this.mainImage = mainImage;
		this.mainImageName = "/main/" + mainImage.getOriginalFilename();
	}
	
	public void setSubImage1(MultipartFile subImage1) {
		this.subImage1 = subImage1;
		this.subImageName1 = "/sub/" + subImage1.getOriginalFilename();
	}
	
	public void setSubImage2(MultipartFile subImage2) {
		this.subImage2 = subImage2;
		this.subImageName2 = "/sub/" + subImage2.getOriginalFilename();
	}
	
	public void setSubImage3(MultipartFile subImage3) {
		this.subImage3 = subImage3;
		this.subImageName3 = "/sub/" + subImage3.getOriginalFilename();
	}
}
