package com.controller;

import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/index")
    public String indexHome() {
        return "index";
    }
}
