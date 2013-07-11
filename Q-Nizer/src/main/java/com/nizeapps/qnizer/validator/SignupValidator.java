package com.nizeapps.qnizer.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nizeapps.qnizer.dom.NizerUser;

@Component
public class SignupValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(NizerUser.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		NizerUser user = (NizerUser)obj;
		validatePassword(user.getPassword(), user.getPasswordConfirm(), errors);
		user.setPassword("");
		user.setPasswordConfirm("");
		validateAgreedToTerms(user.isAgreedToTermsAndConditions(),errors);
		
	}
	
	private void validatePassword(String password, String passwordConfirm, Errors errors ) {
		boolean passwordsPopulated = true;
		if(password==null) {
			errors.reject("password","password.missing");
			passwordsPopulated = false;
		}
		if(passwordConfirm==null) {
			errors.reject("password","password.confirm.missing");
			passwordsPopulated = false;
		}
		if(passwordsPopulated && !password.equals(passwordConfirm)) {
			errors.reject("password","password.confirm.not.matching");
		}
	}
	
	private void validateAgreedToTerms(boolean agreedToTerms, Errors errors) {
		if(!agreedToTerms)
			errors.reject("termsandconditions","terms.conditions.not.agreed");
	}

}
