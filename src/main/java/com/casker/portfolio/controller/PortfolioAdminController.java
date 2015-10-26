/*
 * @(#)PortfolioAdminController.java $version 2015. 10. 23.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;
import com.casker.portfolio.service.PortfolioService;
import com.casker.portfolio.type.SortType;
import com.casker.portfolio.util.ScriptUtil;


/**
 * @author Kanghoon Choi
 */
@RequestMapping("/admin")
@Controller
public class PortfolioAdminController {
	private static final String VIEW_PREFIX = "admin/";
	
	@Autowired
	private PortfolioService portfolioService;
	
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
	 * @param search
	 * @param page
	 * @return
	 */
	@RequestMapping("/portfolio")
	public String getPortfolioList(Model model, PortfolioSearch search, @ModelAttribute Page page) {
		int totalCount = portfolioService.getPortfolioListCount(search);
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(search);
		
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
	 * 포트폴리오 서브이미지영역을 추가
	 * 
	 * @param model
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/manegement/portfolio/subImageForm", method = RequestMethod.GET)
	public String addSubImageForm(Model model, int count) {
		
		model.addAttribute("count", count);
		
		return VIEW_PREFIX + "sub/portfolio_sub_image :: subImage";
	}
	
	/**
	 * 순서 조절
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/portfolio/sort/adjust", method = RequestMethod.POST)
	public String adjustSort(Model model, int sort, SortType sortType) {
		
		portfolioService.adjustPortfolioSort(sort, sortType);
		
		return "success";
	}
}
