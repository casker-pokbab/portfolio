/*
 * @(#)RecentlyAdminController.java $version 2015. 10. 23.
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
import com.casker.portfolio.domain.Recently;
import com.casker.portfolio.domain.RecentlySearch;
import com.casker.portfolio.service.PortfolioService;
import com.casker.portfolio.util.ScriptUtil;


/**
 * @author Kanghoon Choi
 */
@RequestMapping("/admin")
@Controller
public class RecentlyAdminController {
	private static final String VIEW_PREFIX = "admin/";
	
	@Autowired
	private PortfolioService portfolioService;

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
	 * @param search
	 * @param page
	 * @return
	 */
	@RequestMapping("/recently")
	public String getRecentlyList(Model model, RecentlySearch search, @ModelAttribute Page page) {
		int totalCount = portfolioService.getRecentlyListCount(search);
		List<Recently> recentlyList = portfolioService.getRecentlyList(search);
		
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
