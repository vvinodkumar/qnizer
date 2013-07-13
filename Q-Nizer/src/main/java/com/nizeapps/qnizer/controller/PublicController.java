package com.nizeapps.qnizer.controller;

import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nizeapps.qnizer.dom.CachePolicy;
import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.dom.ResponseWrapper;
import com.nizeapps.qnizer.exception.NizerApplicationException;
import com.nizeapps.qnizer.interceptor.CacheControl;
import com.nizeapps.qnizer.service.RegistrationServiceImpl;
import com.nizeapps.qnizer.validator.SignupValidator;

@Controller
@RequestMapping("/public")
@CacheControl(policy = CachePolicy.PRIVATE)
public class PublicController extends BaseController {

	@Autowired
	SignupValidator signUpValidator;
	
	@Autowired
    private RegistrationServiceImpl registrationService;
	
	
	@InitBinder("user")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(signUpValidator);
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
    public String getSignupPage() {
        return "/signup/layout";
    }

	@RequestMapping( value="/business/activate/{emailId}/{activationKey}" , method=RequestMethod.GET)
    public String activateBusinessUser(@PathVariable String emailId, @PathVariable String activationKey) {
		boolean errorOccured = false;
		byte[] decodedEmailId = Base64.decodeBase64(emailId);
		byte[] decodedActivationKey = Base64.decodeBase64(activationKey);
		try {
			
			registrationService.activateUser( new String(decodedEmailId), new String(decodedActivationKey));
		} catch (NizerApplicationException e) {
			errorOccured = true;
			e.printStackTrace();
		} catch (Exception e) {
			errorOccured = true;
			e.printStackTrace();
		}
		if(errorOccured) 
			return "/activate/failed/layout";
		else
			return "/activate/success/layout";
    }
	
	@RequestMapping(value = "/business/signup", method = RequestMethod.POST)
    public @ResponseBody ResponseWrapper addCustomer(@RequestBody @Valid NizerUser user)  throws NizerApplicationException{
		registrationService.signupUser(user);
    	return prepareSuccessResponse(null,null);
    }

}
