/*
 * @(#)Portfolio.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.domain;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * @author Kanghoon Choi
 */
@Data
public class Portfolio {

	private long portfolioNo;
	
	private String portfolioName;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	private Image thumbnailImage;
	
	private String url;
	
	private Image mainImage;
	
	private String title;
	
	private String description;
	
	private String info;
	
	private String participant;
	
	private List<Image> subImageList;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date workDate;
	
	private String displayYN;
	
	private int sort;
	
	public void setDescription(String description) {
		this.description = StringUtils.replace(description, "\r\n", "<br/>");
	}
	
	public void setInfo(String info) {
		this.info = StringUtils.replace(info, "\r\n", "<br/>");
	}
	
	public void setParticipant(String participant) {
		this.participant = StringUtils.replace(participant, "\r\n", "<br/>");
	}
	
//	public void setThumbnailImage(MultipartFile thumbnailImage) {
//		this.thumbnailImage = thumbnailImage;
//		this.thumbnail = "/thumbnail/" + thumbnailImage.getOriginalFilename();
//	}
//	
//	public void setMainImage(MultipartFile mainImage) {
//		this.mainImage = mainImage;
//		this.mainImageName = "/main/" + mainImage.getOriginalFilename();
//	}
//	
//	public void setSubImage1(MultipartFile subImage1) {
//		this.subImage1 = subImage1;
//		this.subImageName1 = "/sub/" + subImage1.getOriginalFilename();
//	}
//	
//	public void setSubImage2(MultipartFile subImage2) {
//		this.subImage2 = subImage2;
//		this.subImageName2 = "/sub/" + subImage2.getOriginalFilename();
//	}
//	
//	public void setSubImage3(MultipartFile subImage3) {
//		this.subImage3 = subImage3;
//		this.subImageName3 = "/sub/" + subImage3.getOriginalFilename();
//	}
}
