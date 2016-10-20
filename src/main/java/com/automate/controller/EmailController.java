package com.automate.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.automate.model.User;
import com.automate.service.EmailServiceInterface;

@Controller
public class EmailController {

	@Autowired
	private EmailServiceInterface emailService;
	
	@RequestMapping(value = "/sendEmail/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> sendEmail(@Valid @PathVariable int id, HttpSession sessionObj) {
		User senderUser = (User) sessionObj.getAttribute("user");
		int senderId = senderUser.getUserId();
		
		emailService.sendAutomatedEmail(senderId, id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
