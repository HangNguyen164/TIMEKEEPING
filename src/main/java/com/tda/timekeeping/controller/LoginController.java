package com.tda.timekeeping.controller;

import com.tda.timekeeping.service.CustomerUserService;
import com.tda.timekeeping.service.impl.AccountImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CustomerUserService customerUserService;

    @Autowired
    private AccountImpl accountImpl;

    @GetMapping(value = {"/", "/index"})
    public String indexHome() {
        return "index";
    }

    @PostMapping(value = {"/index/type-account"})
    public String decentralization(@RequestParam("username") String username, @RequestParam("role") String role, HttpSession session) {
        UserDetails accountExist = customerUserService.loadUserByUsername(username);
        session.setAttribute("account", accountExist);
        if (role.equalsIgnoreCase("USER")) {
            return "redirect:/home-user";
        }
        return "redirect:/home-admin";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

//    @PostMapping(value = "/login")
//    public String login(@RequestParam(value = "password", required = false) String password, HttpSession session) {
//        UserDetails userDetail = (UserDetails) session.getAttribute("account");
//        Account account = accountImpl.login(userDetail.getUsername(), password);
//        System.out.println(account);
//        if (account != null) {
//            return "redirect:/home-admin";
//        }
//        return "login";
//    }

}
