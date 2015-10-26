/*
 * @(#)UserController.java $version 2015. 10. 23.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casker.portfolio.domain.User;
import com.casker.portfolio.exception.UserNotFoundException;
import com.casker.portfolio.service.UserService;


/**
 * @author Kanghoon Choi
 */
@RequestMapping("/admin")
@Controller
public class UserController {
	private static final String VIEW_PREFIX = "admin/";
	
	@Autowired
	private UserService userService;
	
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
	public String login(HttpSession session, User user) {
		
		if (!userService.login(user)) {
			throw new UserNotFoundException(); 
		}
		
		session.setMaxInactiveInterval(60 * 60 * 1);
		session.setAttribute("user", user);
		
		return "redirect:/admin/manegement/portfolio";
	}
	
	/**
	 * 로그아웃
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session, User user) {
		
		session.removeAttribute("user");
		
		return "success";
	}
	
	/**
	 * 패스워드 수정폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/password/editForm", method = RequestMethod.GET)
	public String passwordEditForm(Model model, HttpSession session) {
		
		return VIEW_PREFIX + "sub/sub_password";
	}
	
	/**
	 * 패스워드 수정
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/password/edit", method = RequestMethod.POST)
	public String editPassword(Model model, User user) {
		
		userService.editPassword(user);
		
		return "success";
	}
}
