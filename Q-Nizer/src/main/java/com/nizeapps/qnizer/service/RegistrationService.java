package com.nizeapps.qnizer.service;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.exception.NizerApplicationException;

public interface RegistrationService {
	 public void signupUser(NizerUser user)  throws NizerApplicationException;
	 public void activateUser(String emailId, String activationKey)  throws NizerApplicationException;

}
