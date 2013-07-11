package com.nizeapps.qnizer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nizeapps.qnizer.dom.CachePolicy;
import com.nizeapps.qnizer.dom.NizerUser;
import com.nizeapps.qnizer.dom.ResponseWrapper;
import com.nizeapps.qnizer.exception.ValidationException;
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
	
	@RequestMapping("/signup")
    public String getSignupPage() {
		System.err.println("asdasdas");
        return "/signup/layout";
    }

	@RequestMapping(value = "/business/signup", method = RequestMethod.POST)
    public @ResponseBody ResponseWrapper addCustomer(@RequestBody @Valid NizerUser user)  throws ValidationException{
		registrationService.signupUser(user);
    	return prepareSuccessResponse(null,null);
    }

}
