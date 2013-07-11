package com.nizeapps.qnizer.service;

import java.util.UUID;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.exception.ValidationException;
import com.nizeapps.qnizer.repository.BusinessRepository;

@Service("RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private BusinessRepository businessRepo;
	
	@Override
	public void signupUser(NizerUser user) throws ValidationException {
		if(businessRepo.findBusinessUserByUserName(user.getUserName())!=null) {
    		throw new ValidationException("Email Id used is already registered.",true);
    	}
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(user.getPassword());
		user.setPassword(encryptedPassword);
		businessRepo.registerBusinessUser(user);
 	}

}
