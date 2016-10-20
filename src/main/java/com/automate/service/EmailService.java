package com.automate.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automate.dao.UserDAOInterface;
import com.automate.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
@Transactional
public class EmailService implements EmailServiceInterface {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserDAOInterface userDAO;
	
	public void sendAutomatedEmail(int senderId, int recipientId) {
		
		User sender = userDAO.getUserById(senderId);
		User recipient = userDAO.getUserById(recipientId);
		
		System.out.println(sender.toString());
		System.out.println(recipient.toString());
		
		String msgBody = "Hello " + recipient.getFirstName() + "! " +
				"A potential commuter would like to contact you. " +
				"Please reach out to " + sender.getUserName() + " (" +
				sender.getFirstName() + ") at " + sender.getEmail() +
				" at your earliest convenience. " + 
				"Thanks! " +
				"This has been an AutoMate(d) message from AutoMate.  Please do not reply to this message.";
		
		SimpleMailMessage automatedMessage = new SimpleMailMessage();
		automatedMessage.setFrom("automatecommuterapp@gmail.com");
		automatedMessage.setTo(recipient.getEmail());
		automatedMessage.setSubject("You have a potential commuter match!");
		automatedMessage.setText(msgBody);
		mailSender.send(automatedMessage);
	}
}
