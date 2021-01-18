package com.tda.timekeeping.controller;

import com.tda.timekeeping.security.CustomerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.tda.timekeeping.util.Helper.dispatcher;

@Controller
public class LoginController {

    @Autowired
    private CustomerUserService customerUserService;

    @GetMapping(value = {"/", "/index"})
    public String indexHome(HttpServletRequest request, HttpSession session) {
        String test = request.getHeader("Referer");
        UserDetails account = (UserDetails) session.getAttribute("account");
        System.out.println(account);
        System.out.println(test);
        return "index";
    }

    @PostMapping(value = {"/index/type-account"})
    public String decentralization(@RequestParam("username") String username, @RequestParam("role") String role, HttpSession session) {
        UserDetails accountExist = customerUserService.loadUserByUsername(username);
        session.setAttribute("account", accountExist);
        return dispatcher(role, "redirect:/home-user", "redirect:/home-admin");
    }

    @GetMapping(value = "/login")
    public String login(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/home-admin";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }
}
