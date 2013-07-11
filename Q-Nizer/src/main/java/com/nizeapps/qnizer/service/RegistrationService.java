package com.nizeapps.qnizer.service;

import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.exception.ValidationException;

public interface RegistrationService {
	 public void signupUser(NizerUser user)  throws ValidationException;

}
