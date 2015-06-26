package com.rigil.fda.service;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Override
	public void sendMail(String sendTo, String subject, String body) {	
		try{		  
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(465);
			mailSender.setUsername("fdademoalert");
			mailSender.setPassword("m^BvPw0086v)");
			mailSender.setProtocol("smtps");
			Properties javaMailProperties = new Properties();
			javaMailProperties.setProperty("mail.smtps.auth", "true");
			javaMailProperties.setProperty("mail.smtps.socketFactory.port", "465");
			javaMailProperties.setProperty("mail.smtp.ssl.enable", "true");
			mailSender.setJavaMailProperties(javaMailProperties);
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(sendTo.split(","));
			message.setSubject(subject);
			message.setText(body);
			mailSender.send(message);
			
			logger.debug("Sucessfully sent the email to " + sendTo);
		}
		catch(Exception mailException)
		{
			mailException.printStackTrace();
		}
		
	}

}
