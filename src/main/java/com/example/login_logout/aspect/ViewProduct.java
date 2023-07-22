package com.example.login_logout.aspect;

import com.example.login_logout.model.Product;
import com.example.login_logout.service.ProductService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ViewProduct {
    @Autowired
    ProductService productService;
    @AfterReturning(value = "execution( * com.example.login_logout.controller.UserController.singleProduct(..)) && args( id ,..) ")
    public void viewProduct( int id){
        productService.viewProduct(id);
    }
}
