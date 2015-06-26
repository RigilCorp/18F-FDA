package com.rigil.fda.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rigil.fda.base.AbstractBaseTest;



public class MailServiceTest extends AbstractBaseTest {
	
	@Autowired
	MailService mailService;

	@Test
	public void testEmail() throws Exception {		
        mailService.sendMail(email, "test1", "test1");
	}
	
}
