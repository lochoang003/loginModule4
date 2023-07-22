package com.example.login_logout.controller;

import com.example.login_logout.model.Account;
import com.example.login_logout.service.AccountService;
import com.example.login_logout.validate.CheckUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    CheckUsername checkUsername;
    @GetMapping("/create")
    public String showCreateAccount(Model model) {
        model.addAttribute("account",new Account());
        return "login/create";

    }
    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult, @RequestParam MultipartFile imgFile){
        checkUsername.validate(account,bindingResult);
        if (bindingResult.hasErrors()){
           return "login/create";
        }else{
            accountService.save(account,imgFile);
            return "login/login";
        }


    }

}
