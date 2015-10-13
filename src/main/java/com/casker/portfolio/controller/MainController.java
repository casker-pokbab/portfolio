package com.casker.portfolio.controller;

import java.util.List;

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
import com.casker.portfolio.domain.Project;
import com.casker.portfolio.service.PortfolioService;

@RequestMapping("/main")
@Controller
public class MainController {
	private static final String VIEW_PREFIX = "main/";
	
	@Autowired
	private PortfolioService portfolioService;
	
	/**
	 * 인트로 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public String intro() {
		
		return VIEW_PREFIX + "main/intro";
	}
	
	/**
	 * 메인 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		
		return VIEW_PREFIX + "main/main";
	}
	
	@RequestMapping("/portfolio")
	public String getPortfolioList(Model model, BaseSearch baseSearch) {
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(baseSearch);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "sub/list :: portfolioList";
	}
	
	@RequestMapping("/project")
	public String getProjectList(Model model, BaseSearch baseSearch, @ModelAttribute Page page) {
		int totalCount = portfolioService.getProjectListCount();
		List<Project> projectList = portfolioService.getProjectList(baseSearch);
		
		if (CollectionUtils.isNotEmpty(projectList)) {
			page.setTotalCount(totalCount);
		}
		PageUtil.setPage(page);
		
		model.addAttribute("projectList", projectList);
		
		return VIEW_PREFIX + "sub/list :: projectList";
	}
}
