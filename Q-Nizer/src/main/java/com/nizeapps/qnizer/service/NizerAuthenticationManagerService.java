package com.nizeapps.qnizer.service;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.repository.BusinessRepository;

public class NizerAuthenticationManagerService extends BaseService implements AuthenticationProvider{

	private BusinessRepository businessRepo;
	
	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		
		NizerUser user= businessRepo.findBusinessUserByUserName(auth.getName());
		if(user==null)
			throw new BadCredentialsException("Your credentials are not valid.");
		
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		if(!encryptor.checkPassword((String)auth.getCredentials(), user.getPassword()))
			throw new BadCredentialsException("Your credentials are not valid.");
		 return new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),getAuthorities());
	}
	
	 private List<GrantedAuthority> getAuthorities() {
		 List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		 authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		 return authList;
	 }

	 
	 @Override
	 public boolean supports(Class<? extends Object> authentication) {
	        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	 }

	public BusinessRepository getBusinessRepo() {
		return businessRepo;
	}

	public void setBusinessRepo(BusinessRepository businessRepo) {
		this.businessRepo = businessRepo;
	}
	 
	 
}
