/*
 * @(#)PortfolioMapper.java $version 2015. 10. 10.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import java.util.List;

import com.casker.portfolio.domain.Image;


/**
 * @author Kanghoon Choi
 */
public interface ImageMapper {

	/**
	 * @param image
	 */
	void insertImage(Image image);

	/**
	 * @param imageSearch
	 * @return
	 */
	List<Image> selectImageList(Image imageSearch);

	/**
	 * @param portfolioNo
	 * @return
	 */
	String selectMaxImageId(long portfolioNo);

	/**
	 * @param oldImage
	 */
	void deleteImage(Image oldImage);

}
