package com.example.login_logout.repository;

import com.example.login_logout.model.Rating;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRatingRepo  extends PagingAndSortingRepository<Rating , Integer> {

}
