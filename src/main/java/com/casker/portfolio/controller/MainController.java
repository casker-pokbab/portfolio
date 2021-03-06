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
import org.springframework.web.bind.annotation.ResponseBody;

import com.casker.portfolio.common.Page;
import com.casker.portfolio.common.PageUtil;
import com.casker.portfolio.domain.Contact;
import com.casker.portfolio.domain.Portfolio;
import com.casker.portfolio.domain.PortfolioSearch;
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;
import com.casker.portfolio.service.PortfolioService;
import com.casker.portfolio.service.Publisher;

@Controller
public class MainController {
	private static final String VIEW_PREFIX = "main/";
	
	@Autowired
	private PortfolioService portfolioService;
	
	@Autowired
	private Publisher emailPublisherService;
	
	/**
	 * 인트로 페이지
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/intro", "/portfolio"}, method = RequestMethod.GET)
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
		PortfolioSearch search = new PortfolioSearch();
		search.setDisplayYN("Y");
		search.setPageNum(1);
		search.setPageSize(12);
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(search);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "main/main";
	}
	
	@RequestMapping("/main/portfolio")
	public String getPortfolioList(Model model, PortfolioSearch search) {
		search.setDisplayYN("Y");
		List<Portfolio> portfolioList = portfolioService.getPortfolioList(search);
		
		model.addAttribute("portfolioList", portfolioList);
		
		return VIEW_PREFIX + "sub/list :: portfolioList";
	}
	
	@RequestMapping("/main/recently")
	public String getRecentlyList(Model model, RecentlySearch search, @ModelAttribute Page page) {
		search.setDisplayYN("Y");
		int totalCount = portfolioService.getRecentlyListCount(search);
		List<Recently> recentlyList = portfolioService.getRecentlyList(search);
		
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
	@RequestMapping(value = "/main/portfolio/detail/{portfolioNo}", method = RequestMethod.GET)
	public String recentlyDetail(Model model, @PathVariable long portfolioNo) {
		
		Portfolio portfolio = portfolioService.getPortfolioDetail(portfolioNo);
		
		model.addAttribute("portfolio", portfolio);
		
		return VIEW_PREFIX + "sub/project01";
	}
	
	@ResponseBody
	@RequestMapping(value = "/main/mail/send", method = RequestMethod.POST)
	public String sendEmail(Contact contact) {
		
		if (emailPublisherService.publish(contact)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	/**
	 * 개인정보정책
	 * 
	 * @return
	 */
	@RequestMapping(value = "/main/policy", method = RequestMethod.GET)
	public String getPolicyPage() {
		return VIEW_PREFIX + "sub/policy";
	}
}
