package com.casker.portfolio.controller;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.casker.portfolio.common.Page;
import com.casker.portfolio.common.PageUtil;
import com.casker.portfolio.domain.BaseSearch;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.Recently;
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
	public String getPortfolioList(Model model, BaseSearch<Portfolio> baseSearch) {
		Portfolio portfolio = new Portfolio();
		portfolio.setDisplayYN("Y");
		baseSearch.setDomain(portfolio);
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(baseSearch);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "sub/list :: portfolioList";
	}
	
	@RequestMapping("/recently")
	public String getRecentlyList(Model model, BaseSearch<Recently> baseSearch, @ModelAttribute Page page) {
		Recently recently = new Recently();
		recently.setDisplayYN("Y");
		baseSearch.setDomain(recently);
		int totalCount = portfolioService.getRecentlyListCount();
		List<Recently> recentlyList = portfolioService.getRecentlyList(baseSearch);
		
		if (CollectionUtils.isNotEmpty(recentlyList)) {
			page.setTotalCount(totalCount);
		}
		PageUtil.setPage(page);
		
		model.addAttribute("recentlyList", recentlyList);
		
		return VIEW_PREFIX + "sub/list :: recentlyList";
	}
	
	/**
	 * 캐스커 프로젝트 상세보기
	 * 
	 * @return
	 */
	@RequestMapping(value = "/portfolio/detail/{portfolioNo}", method = RequestMethod.GET)
	public String recentlyDetail(Model model, @PathVariable int portfolioNo) {
		
		Portfolio portfolio = portfolioService.getPortfolioDetail(portfolioNo);
		
		model.addAttribute("portfolio", portfolio);
		
		return VIEW_PREFIX + "sub/project01";
	}
}
