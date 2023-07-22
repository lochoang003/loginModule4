package com.example.login_logout.controller;

import com.example.login_logout.model.Cart;
import com.example.login_logout.model.Product;
import com.example.login_logout.service.CartService;
import com.example.login_logout.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/shop")
public class UserController {
    @Autowired
    ProductService productService;
    @Autowired
    HttpSession session;
    @Autowired
    CartService cartService;

    @GetMapping
    public String shop(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Product> products = productService.getAll(PageRequest.of(page, 2));
        model.addAttribute("products", products);
        return "/shop/shop";
    }

    @GetMapping("/singleProduct")
    public String singleProduct(int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "shop/single-product";
    }
    @GetMapping("/add/{id}")
    public String addCart(@PathVariable int id ){
        cartService.addToCart(id);
        return "redirect:/shop/cart";
    }
    @GetMapping("/cart")
    public String showCart(Model model){
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        double total = 0;
        if (carts != null){
            total = (double) session.getAttribute("total");
        }
        model.addAttribute("total",total);
        model.addAttribute("carts",carts);
        return "/shop/cart";
    }
    @GetMapping("/delete/{id}")
    public String deleteProductCart(  @PathVariable int id){
        cartService.deleteToCart(id);
        return "redirect:/shop/cart";
    }
    @PostMapping("/update")
    public String updateCart(){

        return "redirect:/shop/cart";

    }
    @GetMapping("/checkOut")
    public String showCheckOut( Model model){
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        double total = 0;
        if (carts != null){
            total = (double) session.getAttribute("total");
        }
        model.addAttribute("total",total);
        model.addAttribute("carts",carts);
        return "shop/checkout";
    }
}
