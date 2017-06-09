package com.dono.crud.blog.controller;

import com.dono.crud.blog.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController extends MyAbstractController{


    @GetMapping("/users")
    public String users(Model model) {
        List<Reader> users = readerService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

}
