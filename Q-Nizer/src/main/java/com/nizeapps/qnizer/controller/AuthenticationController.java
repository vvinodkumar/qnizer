package com.nizeapps.qnizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
@RequestMapping("/auth")
public class AuthenticationController extends BaseController {

	 @RequestMapping("/ping")
	 public @ResponseBody String getLoginStatus() {
		 if(RequestContextHolder.currentRequestAttributes().getAttribute("userSecurityCtx", RequestAttributes.SCOPE_SESSION)==null)
			 throw new org.springframework.security.access.AccessDeniedException("User not logged in.");
		 return "";
	 }
}
