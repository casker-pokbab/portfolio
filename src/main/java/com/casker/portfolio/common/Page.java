package com.casker.portfolio.common;

import lombok.Data;

@Data
public class Page {

	private int pageNum = 1;
	private int firstpage = 1;
	private int lastPage = 1;
	private int pageSize = 10;
	private int skipRows = 0;
	private int totalCount;
	private int viewFirst = 1;
	private int viewLast = 1;
	private int pageTopNum;
}
