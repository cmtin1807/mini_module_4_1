package com.example.postsorm.service;

import com.example.postsorm.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAll();
    Post findById(int id);
    void save(Post post);
    void delete(int id);

}
