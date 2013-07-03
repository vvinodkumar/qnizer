package com.nizeapps.qnizer.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@PreAuthorize("hasRole('ROLE_USER')")
public class DashboardController extends BaseController {

	 @RequestMapping("/layout")
	 public String getDashboardPartialPage(ModelMap modelMap) {
		 return "dashboard/layout";
	 }
}
