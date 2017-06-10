package com.dono.crud.blog.controller;

import com.dono.crud.blog.model.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController extends MyAbstractController{

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Post> latest3posts = postsService.getLatest3();
        List<Post> latest5posts = postsService.getLatest5();
        model.addAttribute("latest3posts", latest3posts);
        model.addAttribute("latest5posts", latest5posts);
        return "welcome";
    }

    @GetMapping("/account")
    public String account(Model model) {
        model.addAttribute("account", readerService.getOne(getPrincipal()));
        return "account";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

}
