package com.dono.crud.blog.controller;

import com.dono.crud.blog.model.PageWrapper;
import com.dono.crud.blog.model.Post;
import com.dono.crud.blog.repository.ReaderRepository;
import com.dono.crud.blog.service.PostsService;
import com.dono.crud.blog.service.PostsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
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
