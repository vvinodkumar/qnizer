package com.nizeapps.qnizer.controller;


import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nizeapps.qnizer.dom.Customer;
import com.nizeapps.qnizer.dom.ResponseWrapper;
import com.nizeapps.qnizer.service.CustomerServiceImpl;

/**
 * This controller serves as the entry point for the users.
 * User: Vinod Kumar Vasudevan
 * Date: June 30 2013
 * Time: 11:50 PM
 */
@Controller
@RequestMapping("/home")
@PreAuthorize("hasRole('ROLE_USER')")
public class HomeController extends BaseController {
	
	AtomicInteger i = new AtomicInteger();
	
	@Autowired
    private CustomerServiceImpl customerService;

	@RequestMapping("/customer/list")
    public @ResponseBody ResponseWrapper getCustomerList() {
		 return prepareSuccessResponse(null,customerService.getAllCustomers());
    }
	
	
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public @ResponseBody ResponseWrapper addCustomer(@RequestBody @Valid Customer customer) {
    	customerService.addCustomer(customer);
    	return prepareSuccessResponse(null,null);
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.PUT)
    public @ResponseBody void updateCustomer(@RequestBody @Valid Customer customer) {
    	customerService.updateCustomer(customer);
    }
    
    @RequestMapping(value = "/customer/notify", method = RequestMethod.PUT)
    public @ResponseBody void notifyCustomer(@RequestBody @Valid Customer customer) {
    	customerService.notifyCustomer(customer);
    }

    @RequestMapping(value = "/customer/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeRailwayStation(@PathVariable("id") int tokenId) {
    	customerService.deleteCustomerByTokenId(tokenId);
    }

  	 @RequestMapping("/layout")
	 public String getHomePartialPage(ModelMap modelMap) {
		 return "home/layout";
	 }
}
