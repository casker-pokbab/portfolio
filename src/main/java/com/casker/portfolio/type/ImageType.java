/*
 * @(#)ImageType.java $version 2015. 10. 21.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.type;



/**
 * @author Kanghoon Choi
 */
public enum ImageType {
	
	THUMBNAIL("thumbnail"),	
	MAIN("main"),
	SUB("sub");

	private String filePath;
	
	private ImageType(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public static ImageType valueOfCode(String code) {
		return ImageType.valueOf(code.toUpperCase());
	}
}
