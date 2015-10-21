/*
 * @(#)AdminController.java $version 2015. 10. 13.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casker.portfolio.common.Page;
import com.casker.portfolio.common.PageUtil;
import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.User;
import com.casker.portfolio.service.PortfolioService;
import com.casker.portfolio.service.UserService;
import com.casker.portfolio.util.ScriptUtil;


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
	 * 패스워드 수정폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/password/editForm", method = RequestMethod.GET)
	public String passwordEditForm(Model model, HttpSession session) {
		
		User user = new User();
		user.setId("pokbab");
		session.setAttribute("user", user);
		
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
	public String getPortfolioList(Model model, BaseSearch<Portfolio> baseSearch, @ModelAttribute Page page) {
		int totalCount = portfolioService.getPortfolioListCount();
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(baseSearch);
		
		if (CollectionUtils.isNotEmpty(portfolioList)) {
			page.setTotalCount(totalCount);
		}
		PageUtil.setPage(page);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "sub/portfolio_list :: portfolioList";
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
	@ResponseBody
	@RequestMapping(value = "/manegement/portfolio/write", method = RequestMethod.POST)
	public String writePortfolio(HttpServletResponse response, Portfolio portfolio) {
		
		portfolioService.addPortfolio(portfolio);
		
		response.setCharacterEncoding("UTF-8");
		return ScriptUtil.alertAndRedirect("포트폴리오가 등록되었습니다.", "/admin/manegement/portfolio");
	}
	
	/**
	 * 포트폴리오 수정폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/portfolio/editForm/{portfolioNo}", method = RequestMethod.GET)
	public String editPortfolioForm(Model model, @PathVariable int portfolioNo) {
		
		Portfolio portfolio = portfolioService.getPortfolioDetail(portfolioNo);
		
		model.addAttribute("portfolio", portfolio);
		
		return VIEW_PREFIX + "sub/sub_portfolio_view";
	}
	
	/**
	 * 포트폴리오 수정
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manegement/portfolio/edit", method = RequestMethod.POST)
	public String editPortfolio(HttpServletResponse response, Portfolio portfolio) {
		
		portfolioService.editPortfolio(portfolio);
		
		response.setCharacterEncoding("UTF-8");
		return ScriptUtil.alertAndRedirect("포트폴리오가 수정되었습니다.", "/admin/manegement/portfolio");
	}
	
	/**
	 * 포트폴리오 삭제
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manegement/portfolio/remove", method = RequestMethod.POST)
	public String removePortfolio(Model model, Portfolio portfolio) {
		
		portfolioService.removePortfolio(portfolio);
		
		return "success";
	}
	
	/**
	 * 최근 작업 관리
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/recently", method = RequestMethod.GET)
	public String manegementRecently(Model model) {
		
		return VIEW_PREFIX + "sub/sub_recently";
	}
	
	/**
	 * 최근 작업 리스트
	 * 
	 * @param model
	 * @param baseSearch
	 * @param page
	 * @return
	 */
	@RequestMapping("/recently")
	public String getRecentlyList(Model model, BaseSearch<Recently> baseSearch, @ModelAttribute Page page) {
		int totalCount = portfolioService.getRecentlyListCount();
		List<Recently> recentlyList = portfolioService.getRecentlyList(baseSearch);
		
		if (CollectionUtils.isNotEmpty(recentlyList)) {
			page.setTotalCount(totalCount);
		}
		PageUtil.setPage(page);
		
		model.addAttribute("recentlyList", recentlyList);
		
		return VIEW_PREFIX + "sub/recently_list :: recentlyList";
	}
	
	/**
	 * 최근 작업 등록폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/recently/writeForm", method = RequestMethod.GET)
	public String writeRecentlyForm(Model model) {
		
		return VIEW_PREFIX + "sub/sub_recently_write";
	}
	
	/**
	 * 최근 작업 등록
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manegement/recently/write", method = RequestMethod.POST)
	public String writeRecently(HttpServletResponse response, Recently recently) {
		
		portfolioService.addRecently(recently);
		
		response.setCharacterEncoding("UTF-8");
		return ScriptUtil.alertAndRedirect("최근작업이 등록되었습니다.", "/admin/manegement/recently");
	}
	
	/**
	 * 최근 작업 수정폼
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manegement/recently/editForm/{recentlyNo}", method = RequestMethod.GET)
	public String editRecentlyForm(Model model, @PathVariable long recentlyNo) {
		
		Recently recently = portfolioService.getRecentlyDetail(recentlyNo);
		
		model.addAttribute("recently", recently);
		
		return VIEW_PREFIX + "sub/sub_recently_view";
	}
	
	/**
	 * 최근 작업 수정
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manegement/recently/edit", method = RequestMethod.POST)
	public String editRecently(HttpServletResponse response, Recently recently) {
		
		portfolioService.editRecently(recently);
		
		response.setCharacterEncoding("UTF-8");
		return ScriptUtil.alertAndRedirect("최근작업이 수정되었습니다.", "/admin/manegement/recently");
	}
	
	/**
	 * 최근 작업 삭제
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/manegement/recently/remove", method = RequestMethod.POST)
	public String removeRecently(Model model, Recently recently) {
		
		portfolioService.removeRecently(recently);
		
		return "success";
	}
}
