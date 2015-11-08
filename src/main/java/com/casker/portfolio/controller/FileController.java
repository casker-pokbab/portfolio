/*
 * @(#)FileController.java $version 2015. 10. 21.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casker.portfolio.service.PortfolioService;


/**
 * @author Kanghoon Choi
 */
@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private PortfolioService portfolioService;
	
	/**
	 * 이미지파일을 가져온다
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/portfolio/{portfolioNo}/{imageType}")
	public void getImageFile(HttpServletResponse response, @PathVariable long portfolioNo, 
			@PathVariable String imageType, @RequestParam(defaultValue = "0", required = false) int seq) throws Exception {
		
		File file = portfolioService.getImageFile(portfolioNo, imageType, seq);
		
		response.setContentLength((int)file.length());
		
		String fileName = URLEncoder.encode(file.getName(), "utf-8");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
         
        InputStream inputStream = new FileInputStream(file);
		IOUtils.copy(inputStream, response.getOutputStream());
		IOUtils.closeQuietly(inputStream);

		response.flushBuffer();
	}
}
