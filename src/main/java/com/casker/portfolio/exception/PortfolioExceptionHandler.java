package com.casker.portfolio.exception;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.casker.portfolio.util.ScriptUtil;



/**
 * @author Kanghoon Choi
 */
@Slf4j
@ControllerAdvice("com.casker.portfolio")
public class PortfolioExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseBody String handlerGlobalValidException(HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return ScriptUtil.alertAndBack("아이디나 비밀번호가 틀렸습니다.");
	}
	
}
