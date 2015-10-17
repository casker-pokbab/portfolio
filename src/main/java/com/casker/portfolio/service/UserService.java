/*
 * @(#)UserService.java $version 2015. 10. 15.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import com.casker.portfolio.domain.User;


/**
 * @author Kanghoon Choi
 */
public interface UserService {

	/**
	 * @param user
	 * @return 
	 */
	boolean login(User user);

	/**
	 * @param user
	 */
	void editPassword(User user);

}
