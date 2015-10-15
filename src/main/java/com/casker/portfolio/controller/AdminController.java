/*
 * @(#)AdminController.java $version 2015. 10. 13.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casker.portfolio.common.Page;
import com.casker.portfolio.common.PageUtil;
import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.User;
import com.casker.portfolio.service.PortfolioService;
import com.casker.portfolio.service.UserService;


/**
 * @author Kanghoon Choi
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
	private static final String VIEW_PREFIX = "admin/";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PortfolioService portfolioService;

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
		
		session.setAttribute("user", user);
		
		return "redirect:/admin/manegement/portfolio";
	}
	
	/**
	 * 포트폴리오 관리
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/portfolio", method = RequestMethod.GET)
	public String main(Model model) {
		
		return VIEW_PREFIX + "sub/sub_portfolio";
	}
	
	/**
	 * 포트폴리오 리스트
	 * 
	 * @param model
	 * @param baseSearch
	 * @param page
	 * @return
	 */
	@RequestMapping("/portfolio")
	public String getPortfolioList(Model model, BaseSearch baseSearch, @ModelAttribute Page page) {
		int totalCount = portfolioService.getProjectListCount();
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(baseSearch);
		
		if (CollectionUtils.isNotEmpty(portfolioList)) {
			page.setTotalCount(totalCount);
		}
		PageUtil.setPage(page);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "sub/list :: portfolioList";
	}
	
	/**
	 * 포트폴리오 등록폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/portfolio/writeForm", method = RequestMethod.GET)
	public String writePortfolioForm(Model model) {
		
		return VIEW_PREFIX + "sub/sub_portfolio_write";
	}
	
	/**
	 * 포트폴리오 등록
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/portfolio/write", method = RequestMethod.POST)
	public String writePortfolio(Model model, Portfolio portfolio) {
		
		return VIEW_PREFIX + "sub/sub_portfolio_write";
	}
}
