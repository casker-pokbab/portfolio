package com.casker.portfolio.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.service.PortfolioService;

@Slf4j
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
		
		List<Portfolio> portfolioList = portfolioService.getPortfolioList();
		
		model.addAttribute("portfolioList", portfolioList);
		
		return "main/main";
	}
}
