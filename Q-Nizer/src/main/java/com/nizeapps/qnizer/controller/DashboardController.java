package com.nizeapps.qnizer.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nizeapps.qnizer.dom.CachePolicy;
import com.nizeapps.qnizer.interceptor.CacheControl;

@Controller
@RequestMapping("/dashboard")
@PreAuthorize("hasRole('ROLE_USER')")
@CacheControl(policy = CachePolicy.PRIVATE)
public class DashboardController extends BaseController {

	 @RequestMapping("/layout")
	 public String getDashboardPartialPage(ModelMap modelMap) {
		 return "dashboard/layout";
	 }
}
