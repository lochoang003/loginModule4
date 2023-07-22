package com.example.login_logout.controller;

import com.example.login_logout.service.AccountService;
import com.example.login_logout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AccountService accountService;
    @GetMapping
    public String showLogin(){
        return "login/login";
    }
    @PostMapping
    public String login(String username , String password , Model model){
        String checkLogin = accountService.checkLogin(username,password);
        if (checkLogin.equals("admin"))
            return "redirect:/admin";
        else if (checkLogin.equals("user"))
            return "redirect:/shop";
        else {
            model.addAttribute("err","account, password incorrect");
            return "login/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("account",null);
        return "login/login";
    }
}
