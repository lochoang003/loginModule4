package com.example.login_logout.repository;

import com.example.login_logout.model.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICratRepo extends PagingAndSortingRepository<Cart, Integer> {
}
