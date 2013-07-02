package com.nizeapps.qnizer.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nizeapps.qnizer.dom.Customer;
import com.nizeapps.qnizer.dom.ResponseWrapper;
import com.nizeapps.qnizer.exception.ValidationException;
import com.nizeapps.qnizer.service.CustomerServiceImpl;

/**
 * This controller serves as the entry point for the users.
 * User: Vinod Kumar Vasudevan
 * Date: June 30 2013
 * Time: 11:50 PM
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
	
	@Autowired
    private CustomerServiceImpl customerService;

	@RequestMapping("/customer/list")
    public @ResponseBody ResponseWrapper getCustomerList() {
		 return prepareSuccessResponse(null,customerService.getAllCustomers());
    }
	
    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public @ResponseBody ResponseWrapper addCustomer(@RequestBody @Valid Customer customer) {
    	customer.setCustomerFirstContactTime();
    	customerService.addCustomer(customer);
    	return prepareSuccessResponse(null,null);
    }

    @RequestMapping(value = "/customer/update", method = RequestMethod.PUT)
    public @ResponseBody void updateCustomer(@RequestBody @Valid Customer customer) {
    	customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/customer/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeRailwayStation(@PathVariable("id") int tokenId) {
    	customerService.deleteCustomerByTokenId(tokenId);
    }

    @RequestMapping(value = "/customer/removeAll", method = RequestMethod.DELETE)
    public @ResponseBody void removeAllCustomers() {
    	customerService.deleteAll();
    }

	 @RequestMapping("/layout")
	 public String getHomePartialPage(ModelMap modelMap) {
		 return "home/layout";
	 }
}
