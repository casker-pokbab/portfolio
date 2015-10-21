/*
 * @(#)ScriptUtil.java $version 2015. 10. 22.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.util;


/**
 * @author Kanghoon Choi
 */
public class ScriptUtil {

	private ScriptUtil() {	}
	
	public static String alert(String message) {
		return appendScriptTag("alert(\"" + message + "\");");
	}
	
	public static String alertAndBack(String message) {
		return appendScriptTag("alert(\"" + message + "\");history.back();");
	}
	
	public static String alertAndRedirect(String message, String url) {
		return appendScriptTag("alert(\"" + message + "\");location.href=\"" + url + "\";");
	}
	
	private static String appendScriptTag(String script) {
		return "<html><script>" + script + "</script>";
	}
}
