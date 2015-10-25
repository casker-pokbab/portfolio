/*
 * @(#)EmailPublisherService.java $version 2015. 10. 24.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.casker.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.casker.portfolio.domain.Contact;


/**
 * @author Kanghoon Choi
 */
@Service
public class EmailPublisherService implements Publisher {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("#{mail['receiver.address']}")
	private String receiverAddress;
	
	
	@Override
	public <T> boolean publish(T report) {
		if (!(report instanceof Contact)) {
			return false;
		}
		
		Contact contact = (Contact)report;
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(contact.getSenderAddress());
		message.setTo(receiverAddress);
		message.setSubject(contact.getName() + "/" + contact.getCompany() + "/" + contact.getProject());
		message.setText(contact.getTel() + "\n\n" + contact.getBudget() + "\n\n" + contact.getMessage());
		javaMailSender.send(message);
		
		return true;
	}

}
