package com.dono.crud.blog.controller;

import com.dono.crud.blog.repository.ReaderRepository;
import com.dono.crud.blog.service.PostsService;
import com.dono.crud.blog.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class MyAbstractController {
    @Autowired
    PostsService postsService;

    @Autowired
    ReaderService readerService;

    String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
