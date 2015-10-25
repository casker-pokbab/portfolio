/*
 * @(#)Publisher.java $version 2015. 10. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;


/**
 * @author Kanghoon Choi
 */
public interface Publisher {

	public <T> boolean publish(T report);
}
