package com.dono.crud.blog.service;

import com.dono.crud.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsService {

    Page<Post> getAll(Pageable pageable);

    Page<Post> getAllByUsername(String string, Pageable pageable);

    List<Post> getLatest5();

    List<Post> getLatest3();

    Post getById(int id);

    Page<Post> getPage(Pageable pageable);

    Post getLast();

    void save(Post post);

    void delete(int id);
}
