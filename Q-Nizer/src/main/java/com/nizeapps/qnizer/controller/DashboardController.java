package com.nizeapps.qnizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

	 @RequestMapping("/layout")
	 public String getDashboardPartialPage(ModelMap modelMap) {
		 return "dashboard/layout";
	 }
}
