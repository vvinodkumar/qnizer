package com.nizeapps.qnizer.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller serves as the entry point for the users.
 * User: Vinod Kumar Vasudevan
 * Date: June 30 2013
 * Time: 11:50 PM
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String getIndexPage() {
        return "index";
    }
    
    
}
