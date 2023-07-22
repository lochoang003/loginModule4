package com.example.login_logout.repository;

import com.example.login_logout.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRole extends CrudRepository<Role,Integer> {
}
