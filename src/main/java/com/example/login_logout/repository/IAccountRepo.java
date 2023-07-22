package com.example.login_logout.repository;

import com.example.login_logout.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface IAccountRepo extends CrudRepository<Account,Integer> {
}
