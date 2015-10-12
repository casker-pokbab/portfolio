/*
 * @(#)ProjectMapper.java $version 2015. 10. 8.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import java.util.List;

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Project;


/**
 * @author Kanghoon Choi
 */
public interface ProjectMapper {

	/**
	 * @param baseSearch 
	 * @return
	 */
	List<Project> selectProjectList(BaseSearch baseSearch);

}
