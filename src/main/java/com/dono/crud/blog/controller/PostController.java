package com.dono.crud.blog.controller;

import com.dono.crud.blog.model.PageWrapper;
import com.dono.crud.blog.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class PostController extends MyAbstractController {

    @GetMapping("/posts")
    public String posts(@PageableDefault(value = 4, page = 0) Pageable pageable, Model model) {
        boolean authenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        Page<Post> page;
        if (authenticated) {
            page = postsService.getAll(pageable);
        } else {
            page = postsService.getAllByUsername(getPrincipal(), pageable);
        }
        PageWrapper<Post> pageWrapper = new PageWrapper<>(page, "/posts");
        model.addAttribute("posts", pageWrapper.getContent());
        model.addAttribute("page", pageWrapper);
        return "posts";
    }

    @GetMapping("/post/view/{id}")
    public String viewPost(@PathVariable int id, Model model) {
        model.addAttribute("post", postsService.getById(id));
        return "postShow";
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable int id, Model model) {
        model.addAttribute("post", postsService.getById(id));
        model.addAttribute("isEditing", true);
        return "postForm";
    }

    @GetMapping("/post/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("isEditing", false);
        return "postForm";
    }

    @PostMapping("/post")
    public String savePost(@Valid Post post, BindingResult result) {
        //todo validate
        post.setCreatedDate(LocalDate.now());
        post.setReader(readerService.getOne(getPrincipal()));
        postsService.save(post);
        return "redirect:/post/view/" + post.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable int id, Model model) {
        postsService.delete(id);
        return "redirect:/posts";
    }

}
