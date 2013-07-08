package com.nizeapps.qnizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController extends BaseController {

	@RequestMapping("/welcome")
    public String getPublicPage() {
        return "/public/index";
    }
	
	@RequestMapping("/signup")
    public String getSignupPage() {
        return "/signup/layout";
    }
}
