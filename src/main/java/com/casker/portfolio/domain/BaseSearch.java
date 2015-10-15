/*
 * @(#)BaseSearch.java $version 2015. 10. 12.
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
public class BaseSearch {
	private int pageNum = 1;
	private int pageSize = 12;
	private int skipRows;
	private boolean all = false;
	
	//정렬
	private String orderFieldName;
	private Integer orderType; //1 = ASCE -1 = DESC
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.skipRows = ((pageNum < 1 ? 1 : pageNum) - 1) * pageSize; 
	}
	
	public boolean getAll() {
		return all;
	}
}
