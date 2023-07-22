package com.example.login_logout.service;

import com.example.login_logout.model.Account;
import com.example.login_logout.repository.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    IAccountRepo accountRepo;
    @Autowired
    HttpSession session;

    public List<Account> getAll() {
        return (List<Account>) accountRepo.findAll();
    }

    public void save(Account account, MultipartFile file) {
        String nameFile = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\login\\login_logout\\src\\main\\webapp\\imgAccount/" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        account.setAvata("/imgAccount/" + nameFile);
        accountRepo.save(account);
    }
    public void save(Account account, MultipartFile file, int idRole) {
        String nameFile = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\login\\login_logout\\src\\main\\webapp\\imgAccount/" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        account.getRole().setId(idRole);
        account.setAvata("/imgAccount/" + nameFile);
        accountRepo.save(account);
    }



    public boolean checkUsername(String username) {
        List<Account> accounts = getAll();
        for (Account a : accounts) {
            if (a.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public String checkLogin(String username, String password) {
        List<Account> accounts = getAll();
        for (Account a : accounts) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
                session.setAttribute("account", a);
                if (a.getRole().getName().equals("admin")) {
                    return "admin";
                } else {
                    return "user";
                }
            }
        }
        return "null";
    }
    public Account findById(int id){
        List<Account>   accounts = getAll();
        for (Account a:accounts) {
            if (a.getId() == id)
                return a;
        }
        return new Account();
    }
    public void delete(int id){
        accountRepo.deleteById(id);
    }
}

