/*
 * @(#)AdminController.java $version 2015. 10. 13.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Kanghoon Choi
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
	private static final String VIEW_PREFIX = "admin/";

	/**
	 * 어드민 로그인폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String loginForm(Model model) {
		
		return VIEW_PREFIX + "main/main";
	}
	
	/**
	 * 로그인
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model) {
		
		return VIEW_PREFIX + "sub/sub_portfolio";
	}
}
