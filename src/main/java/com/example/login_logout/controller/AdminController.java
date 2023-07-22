package com.example.login_logout.controller;

import com.example.login_logout.model.Account;
import com.example.login_logout.model.Product;
import com.example.login_logout.service.AccountService;
import com.example.login_logout.service.ProductService;
import com.example.login_logout.validate.CheckProduct;
import com.example.login_logout.validate.CheckUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    HttpSession session;
    @Autowired
    ProductService productService;
    @Autowired
    AccountService accountService;
    @Autowired
    CheckProduct checkProduct;
    @Autowired
    CheckUsername checkUsername;

    @GetMapping
    public String showAdmin() {
        Account account = (Account) session.getAttribute("account");
        if (!account.getRole().getName().equals("admin"))
            return "redirect:/shop";
        else return "admin/adminHome";
    }

    @GetMapping("/product")
    public String showProduct(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin/product";
    }

    @GetMapping("/user")
    public String showAccount(Model model) {
        model.addAttribute("accounts", accountService.getAll());
        return "admin/account";
    }

    @GetMapping("/create")
    public String showCreateProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, @RequestParam MultipartFile imgFile) {
        checkProduct.validate(product, bindingResult);
        if (bindingResult.hasErrors()) {
            return "admin/createProduct";
        } else {
            productService.save(product, imgFile);
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/edit")
    public String showEditProductt(int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "admin/editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(Product product, @RequestParam MultipartFile imgFile) {
        productService.save(product, imgFile);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete")
    public String delete(int id) {
        productService.delete(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String showEditUser(@PathVariable int id, Model model) {
        model.addAttribute("user", accountService.findById(id));
        return "admin/editUser";
    }

    @PostMapping("/editUser")
    public String editUser(@Valid @ModelAttribute("user") Account user, BindingResult bindingResult, int idRole, @RequestParam MultipartFile imgFile) {
        if (bindingResult.hasErrors()) {
            return "admin/editUser";
        } else {
            accountService.save(user, imgFile, idRole);
            return "redirect:/admin/user";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        accountService.delete(id);
        return "redirect:/admin/user";


    }

}
