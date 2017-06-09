package com.dono.crud.blog.service;

import com.dono.crud.blog.model.Post;
import com.dono.crud.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Page<Post> getAllByUsername(String string, Pageable pageable) {
        return postRepository.findAllByReaderUsername(string, pageable);
    }

    @Override
    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> getLatest5() {
        return postRepository.findTop5ByOrderByCreatedDateDesc();
    }

    public List<Post> getLatest3() {
        return postRepository.findTop3ByOrderByCreatedDateDesc();
    }

    @Override
    public Post getLast() {
        return postRepository.findTopByOrderByCreatedDateDesc();
    }

    public Post getById(int id) {
        return postRepository.findOne(id);
    }

    public Page<Post> getPage(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(int id) {
        postRepository.delete(id);
    }
}
