package com.casker.portfolio.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.casker.portfolio.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/admin/loginForm");
			return false;
		}

		return super.preHandle(request, response, handler);
	}

}
