package com.nizeapps.qnizer.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.EncoderConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.util.DateUtility;


public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final String USER_CONTEXT="USER_CONTEXT";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)a.getPrincipal();
		NizerUser user = new NizerUser();
		user.setUserName(currentUser.getUsername());
		user.setLastLoggedInDateTime(DateUtility.getBusinessDateTime());
		user.setCsrfToken(ESAPI.randomizer().getRandomString(8,  EncoderConstants.CHAR_ALPHANUMERICS));
		request.getSession(true).setAttribute(USER_CONTEXT, user);
		
  }
}
