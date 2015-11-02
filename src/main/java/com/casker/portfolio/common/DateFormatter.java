/*
 * @(#)DateFormatter.java $version 2015. 11. 3.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

/**
 * @author Kanghoon Choi
 */
public class DateFormatter implements Formatter<Date> {

	@Autowired
	private MessageSource messageSource;

	public DateFormatter() {
		super();
	}

	public Date parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.parse(text);
	}

	public String print(Date object, Locale locale) {
		SimpleDateFormat dateFormat = createDateFormat(locale);
		return dateFormat.format(object);
	}

	private SimpleDateFormat createDateFormat(Locale locale) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		return dateFormat;
	}

}
