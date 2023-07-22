package com.example.login_logout.repository;

import com.example.login_logout.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepo extends PagingAndSortingRepository<Comment, Integer> {

}
