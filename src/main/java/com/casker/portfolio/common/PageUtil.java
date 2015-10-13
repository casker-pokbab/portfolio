/*
 * @(#) PagingUtil.java 2014. 10. 15 
 *
 * Copyright 2014 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Kanghoon Choi.
 */
@Slf4j
public class PageUtil {
	public static final int DEFAULT_DISPLAY_ROW_NUMBER = 5;
	
	public static void setPage(Page page) {
		int currentPage = page.getPageNum();
		int totalCount = page.getTotalCount();
		int pageSize = page.getPageSize();
		int lastPage = (totalCount / pageSize) + 1;
		
		if (totalCount == 0) {
			page.setLastPage(1);
			page.setViewFirst(1);
			page.setViewLast(1);
			page.setSkipRows(1);
			page.setPageTopNum(1);
			
			return;
		}
		
		if (totalCount % pageSize == 0) {
			lastPage = lastPage - 1;
		}
		
		int viewFirst = (currentPage - 5) <= 0 ? 1 : (currentPage - 5);
		int viewLast = viewFirst + DEFAULT_DISPLAY_ROW_NUMBER - 1;
		
		if (viewLast > lastPage) {
			viewLast = lastPage;
		}
		
		int skipRows = (currentPage - 1) * pageSize;
		int pageTopNum = totalCount -  (currentPage - 1) * pageSize;
		
		page.setLastPage(lastPage);
		page.setViewFirst(viewFirst);
		page.setViewLast(viewLast);
		page.setSkipRows(skipRows);
		page.setPageTopNum(pageTopNum);
		
		log.debug(page.toString());
	}
}
