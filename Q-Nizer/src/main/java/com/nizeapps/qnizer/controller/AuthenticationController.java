package com.nizeapps.qnizer.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.exception.NizerApplicationException;
import com.nizeapps.qnizer.service.UserServiceImpl;

@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {
	
	private final String USER_CONTEXT="USER_CONTEXT";

	@Autowired
	UserServiceImpl userService;
	
	 @RequestMapping(value="/ping", method=RequestMethod.GET)
	 public @ResponseBody String getLoginStatus(HttpServletRequest request) throws NizerApplicationException {
		 
		 NizerUser user = (NizerUser) request.getSession().getAttribute(USER_CONTEXT); 
		 if(user ==null)
			 throw new org.springframework.security.access.AccessDeniedException("User not logged in.");
		 NizerUser userDetails = userService.fetchUserDetails(user.getUserName());
		 userDetails.setCsrfToken(user.getCsrfToken());
		 request.getSession().setAttribute(USER_CONTEXT,userDetails); 
		 return "";
	 }
}
