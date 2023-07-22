package com.example.login_logout.repository;

import com.example.login_logout.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductRepo extends PagingAndSortingRepository<Product,Integer> {
}
