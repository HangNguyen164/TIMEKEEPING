package com.tda.timekeeping.controller;

import com.tda.timekeeping.service.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private CustomerUserService customerUserService;

    @GetMapping(value = {"/", "/index"})
    public String indexHome() {
        return "index";
    }

    @PostMapping(value = {"/index/type-account"})
    public String decentralization(@RequestParam("username") String username, @RequestParam("role") String role, HttpSession session) {
        UserDetails accountExist = customerUserService.loadUserByUsername(username);
        if (accountExist != null) {
            session.setAttribute("username", accountExist.getUsername());
            if (role.equalsIgnoreCase("USER")) {
                return "redirect:/home-user";
            } else {
                return "redirect:/login";
            }
        } else {
            return "redirect:/index";
        }
    }

    @RequestMapping(value = {"/login"})
    public String login(@RequestParam(value = "password", required = false) String password, HttpSession session) {
        if (password != null) {
            String username = (String) session.getAttribute("username");
//            Account account=accountImpl.login(username,password);
//            if(account!=null){
//                return "redirect:/home-admin";
//            }
        }
        return "login";
    }

}
