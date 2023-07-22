package com.example.login_logout.validate;
import com.example.login_logout.model.Account;
import com.example.login_logout.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class CheckUsername implements Validator {
    @Autowired
    AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
       if (accountService.checkUsername(account.getUsername())){
           errors.rejectValue("username","","Already have an account");
       }

        }
}
