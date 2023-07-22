package com.example.login_logout.service;


import com.example.login_logout.model.Cart;
import com.example.login_logout.model.Product;
import com.example.login_logout.repository.ICratRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    ICratRepo cartRepo;
    @Autowired
    HttpSession session;
    @Autowired
    ProductService productService;


    public List<Cart> getAll() {
        return (List<Cart>) cartRepo.findAll();
    }

    public void save(Cart cart) {
        cartRepo.save(cart);

    }


    public void delete(Cart cart) {
        cartRepo.delete(cart);
    }

    public void delete(int id) {
        cartRepo.deleteById(id);
    }

    public boolean deleteToCart(int idP) {
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        if (carts == null) {
            return false;
        }
        int checkP = checkProductCart(idP, carts);
       if (checkP != -1){
           carts.remove(checkP);
           session.setAttribute("carts", carts);
           session.setAttribute("total", totalCart(carts));
           return true;
       }
       return false;

    }

    public void addToCart(int id) {
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");
        if (carts == null) {
            carts = new ArrayList<>();
        }
        Product product = productService.findById(id);
        int checkP = checkProductCart(id, carts);
        if (checkP != -1) {
            int quantyti = carts.get(checkP).getQuantity();
            double total = carts.get(checkP).getTotal();
            carts.get(checkP).setQuantity(quantyti + 1);
            carts.get(checkP).setTotal(total + product.getPrice());
        } else {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setTotal(product.getPrice());
            cart.setQuantity(1);
            carts.add(cart);
        }
        session.setAttribute("carts", carts);
        session.setAttribute("total", totalCart(carts));
    }

    private double totalCart(List<Cart> carts) {
        double total = 0;
        for (int i = 0; i < carts.size(); i++) {
            total += carts.get(i).getTotal();
        }
        return total;
    }

    private int checkProductCart(int id, List<Cart> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
