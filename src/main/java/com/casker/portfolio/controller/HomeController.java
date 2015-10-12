package com.casker.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Project;
import com.casker.portfolio.service.PortfolioService;

@Controller
public class HomeController {
	
	@Autowired
	private PortfolioService portfolioService;
	
	/**
	 * 인트로 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/intro", method = RequestMethod.GET)
	public String intro() {
		
		return "main/intro";
	}
	
	/**
	 * 메인 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		
		return "main/main";
	}
	
	@RequestMapping("/portfolio")
	public String getPortfolioList(Model model, BaseSearch baseSearch) {
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(baseSearch);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return "sub/list :: portfolioList";
	}
	
	@RequestMapping("/project")
	public String getProjectList(Model model, BaseSearch baseSearch) {
		List<Project> projectList = portfolioService.getProjectList(baseSearch);
		
		model.addAttribute("projectList", projectList);
		
		return "sub/list :: projectList";
	}
}
