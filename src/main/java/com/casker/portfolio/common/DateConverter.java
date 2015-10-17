/*
 * @(#)DateConverter.java $version 2015. 10. 17.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Kanghoon Choi
 */
@Slf4j
public class DateConverter implements Converter<String, Date> {

	private final SimpleDateFormat formatter;

	public DateConverter(String pattern) {
		this.formatter = new SimpleDateFormat(pattern);
	}

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}

		try {
			return formatter.parse(source);
		} catch (ParseException e) {
			log.error("잘못된 날짜 패턴", e);
			return null;
		}
	}
}
