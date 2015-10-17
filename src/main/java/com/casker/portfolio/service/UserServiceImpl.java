/*
 * @(#)UserServiceImpl.java $version 2015. 10. 15.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casker.portfolio.domain.User;
import com.casker.portfolio.mapper.UserMapper;


/**
 * @author Kanghoon Choi
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean login(User user) {
		return (userMapper.selectUserCount(user) == 1) ? true : false;
	}

	@Override
	public void editPassword(User user) {
		userMapper.updateUser(user);
	}
}
