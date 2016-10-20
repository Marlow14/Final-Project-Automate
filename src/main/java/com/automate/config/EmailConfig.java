package com.automate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import org.springframework.context.annotation.Bean;

@Configuration
public class EmailConfig {
	
	@Bean
	public MailSender mailSender(){
		
		Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", "auth");
        mailProperties.put("mail.smtp.starttls.enable", "starttls");
        mailProperties.put("mail.smtp.starttls.required", "startlls_required");
        mailProperties.put("mail.smtp.socketFactory.port", "socketPort");
        mailProperties.put("mail.smtp.debug", "debug");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", "fallback");
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setUsername("automatecommuterapp");
		mailSender.setPassword("automate8");
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}
	
}

