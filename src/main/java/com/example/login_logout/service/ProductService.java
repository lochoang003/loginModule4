package com.example.login_logout.service;

import com.example.login_logout.model.Account;
import com.example.login_logout.model.Product;
import com.example.login_logout.repository.IAccountRepo;
import com.example.login_logout.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService  {
    @Autowired
    IAccountRepo accountRepo;

    @Autowired
    IProductRepo productRepo;


    public List<Product> getAll() {
        return (List<Product>) productRepo.findAll();
    }
    public Page<Product> getAll(Pageable pageable) {
        return  productRepo.findAll(pageable);
    }

    public void save(Product product , MultipartFile file) {
        String nameFile = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\login\\login_logout\\src\\main\\webapp\\imgProduct/" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImage("/imgProduct/"+nameFile);
        productRepo.save(product);
    }



    public void delete(Product product) {
        productRepo.delete(product);
    }
    public void delete(int id) {
        productRepo.deleteById(id);
    }
    public boolean checkProduct(String nameProduct){
        List<Product> products = getAll();
        for (Product p: products) {
            if (p.getName().equals(nameProduct)){
                return true;
            }
        }
        return false;

    }
    public Product findById(int id){
        List<Product> products = getAll();
        for (Product p: products) {
            if (p.getId() == id)
                return  p;
        }
        return new Product();
    }
    public void viewProduct(int id){
        Product product = findById(id);
        product.setView(product.getView() + 1);
        productRepo.save(product);
    }

}
