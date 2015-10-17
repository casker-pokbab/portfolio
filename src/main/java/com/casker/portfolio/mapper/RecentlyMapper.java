/*
 * @(#)ProjectMapper.java $version 2015. 10. 8.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import java.util.List;

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Recently;


/**
 * @author Kanghoon Choi
 */
public interface RecentlyMapper {

	/**
	 * @param baseSearch 
	 * @return
	 */
	List<Recently> selectRecentlyList(BaseSearch<Recently> baseSearch);

	/**
	 * @return
	 */
	int selectRecentlyListCount();

	/**
	 * @param recently
	 */
	void insertRecently(Recently recently);

	/**
	 * @param recently
	 */
	void deleteRecently(Recently recently);

}
