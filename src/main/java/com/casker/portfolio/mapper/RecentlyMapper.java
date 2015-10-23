/*
 * @(#)ProjectMapper.java $version 2015. 10. 8.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import java.util.List;

import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;


/**
 * @author Kanghoon Choi
 */
public interface RecentlyMapper {

	/**
	 * @param search 
	 * @return
	 */
	List<Recently> selectRecentlyList(RecentlySearch search);

	/**
	 * @return
	 */
	int selectRecentlyListCount(RecentlySearch search);

	/**
	 * @param recentlyNo
	 * @return
	 */
	Recently selectRecently(long recentlyNo);

	/**
	 * @param recently
	 */
	void insertRecently(Recently recently);

	/**
	 * @param recently
	 */
	void updateRecently(Recently recently);

	/**
	 * @param recently
	 */
	void deleteRecently(Recently recently);


}
