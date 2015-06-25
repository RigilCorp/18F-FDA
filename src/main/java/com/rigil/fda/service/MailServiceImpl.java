package com.rigil.fda.service;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailServiceImpl implements MailService {
	
	//@Autowired
	//JavaMailSenderImpl mailSender;
	
	@Override
	public void sendMail(String sendTo, String subject, String body) {	
		try{		
			/*
		   MimeMessage mimeMessage = mailSender.createMimeMessage();
		   MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);
	       mailMsg.setFrom("fdademoalert@gmail.com");
	       mailMsg.setTo(sendTo);
	       mailMsg.setSubject(subject);
	       mailMsg.setText(body);
		   mailSender.send(mimeMessage);
		   System.out.println("---Done---");
			 */  
			  /*
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(465);
			mailSender.setUsername(username);
			mailSender.setPassword(password);
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
			message.setFrom(username);
			mailSender.send(message);
			*/
			System.out.println("Sucessfully sent the email to " + sendTo);
		}
		catch(Exception mailException)
		{
			mailException.printStackTrace();
		}
		
	}

}
