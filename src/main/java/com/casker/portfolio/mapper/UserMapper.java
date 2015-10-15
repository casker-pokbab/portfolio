/*
 * @(#)UserMapper.java $version 2015. 10. 15.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.mapper;

import com.casker.portfolio.domain.User;


/**
 * @author Kanghoon Choi
 */
public interface UserMapper {

	/**
	 * @param user
	 * @return
	 */
	int selectUserCount(User user);

}
