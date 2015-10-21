/*
 * @(#)ImageType.java $version 2015. 10. 21.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.type;

import com.casker.portfolio.domain.Portfolio;


/**
 * @author Kanghoon Choi
 */
public enum ImageType {
	
	THUMBNAIL() {
		@Override
		public String getFilePath(Portfolio portfolio) {
			return portfolio.getThumbnail();
		}
	},	
	MAIN() {
		@Override
		public String getFilePath(Portfolio portfolio) {
			return portfolio.getMainImageName();
		}
	},
	SUB1() {
		@Override
		public String getFilePath(Portfolio portfolio) {
			return portfolio.getSubImageName1();
		}
	},	
	SUB2() {
		@Override
		public String getFilePath(Portfolio portfolio) {
			return portfolio.getSubImageName2();
		}
	},
	SUB3() {
		@Override
		public String getFilePath(Portfolio portfolio) {
			return portfolio.getSubImageName3();
		}
	};
	
	public static ImageType valueOfCode(String code) {
		return ImageType.valueOf(code.toUpperCase());
	}
	
	public abstract String getFilePath(Portfolio portfolio);
}
