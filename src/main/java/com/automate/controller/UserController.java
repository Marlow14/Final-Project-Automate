package com.automate.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.automate.service.UserServiceInterface;
import com.automate.model.User;

@Controller
public class UserController {

	@Autowired
	private UserServiceInterface userService;

	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping(value = "/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		mv.setViewName("index");
		if (request.getParameter("username") != null) {
			String name = request.getParameter("name");  
			mv.addObject("username", request.getParameter("username"));
		}
		if (request.getParameter("password") != null) {
			String password = request.getParameter("password");
			mv.addObject("password", request.getParameter("password"));
		}
		return mv;
	}

	@RequestMapping(value = "/profile")
	public ModelAndView profile(HttpServletRequest request, HttpServletRequest response, ModelAndView mv, HttpSession sessionObj) throws IOException {
		if(sessionObj.getAttribute("user") == null){
			return new ModelAndView("redirect:/");
		}
		mv.setViewName("profile");
		return mv;
	}

	@RequestMapping(value = "/matches")
	public ModelAndView matches(HttpServletRequest request, HttpServletRequest response, ModelAndView mv, HttpSession sessionObj) throws IOException {
		if(sessionObj.getAttribute("user") == null){
			return new ModelAndView("redirect:/");
		}
		mv.setViewName("matches");
		return mv;
	}

	@ExceptionHandler(SpelEvaluationException.class)
	public ModelAndView pageNotFoundHandler(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/index");
		return mv;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> userPerson(@ModelAttribute User user, HttpSession sessionObj) throws IOException {
		int savedId = userService.addUser(user);
		if (savedId == 0) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		user.setUserId(savedId);
		sessionObj.setAttribute("user", user);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@ModelAttribute User user, HttpSession sessionObj) {
		userService.updateUser(user);
		sessionObj.setAttribute("user", user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> User(@PathVariable("id") Integer userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/userMatch", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getMatches(HttpSession sessionObj, Model model) {
		User userInfo = (User) sessionObj.getAttribute("user");
		List<User> userMatches = userService.getMatches(userInfo.getHomeLat(), userInfo.getHomeLng(), userInfo.getWorkLat(), userInfo.getWorkLng());
		sessionObj.setAttribute("matches", userMatches);
		return new ResponseEntity<List<User>>(userMatches, HttpStatus.OK); 
	}	 

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST)
	public ResponseEntity<List<User>> userLoginPlus(@Valid @RequestBody User member, HttpSession sessionObj) {
		List<User> success = userService.verifyPassword(member.getUserName(), member.getPassword());
		String isValid = member.getPassword();
		if (!(success.get(0).getPassword().equals(isValid))) {
			sessionObj.setAttribute("error", "Username or password invalid!");
			return new ResponseEntity<List<User>>(HttpStatus.CONFLICT);
		} else {
			sessionObj.setAttribute("user", success.get(0));
			return new ResponseEntity<List<User>>(success, HttpStatus.OK); 
		}
	}

	@RequestMapping(value = "/userlogout")
	public ModelAndView userLogout(HttpSession sessionObj, ModelAndView mv) {
		mv.setViewName("index");
		sessionObj.invalidate();
		return mv;
	}
}
