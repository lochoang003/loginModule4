package com.example.login_logout.validate;
import com.example.login_logout.model.Account;
import com.example.login_logout.model.Product;
import com.example.login_logout.service.AccountService;
import com.example.login_logout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class CheckProduct implements Validator {
    @Autowired
    ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
       if (productService.checkProduct(product.getName())){
           errors.rejectValue("name","","product already exists");
       }

        }
}
