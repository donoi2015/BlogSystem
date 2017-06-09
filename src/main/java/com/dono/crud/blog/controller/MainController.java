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
@RequestMapping("/")
public class MainController {

    @Autowired
    PostsService postsService;

    @Autowired
    ReaderRepository readerRepository;

    @GetMapping(value = {"/", "/home"})
    public String home(Model model) {
        List<Post> latest3posts = postsService.getLatest3();
        List<Post> latest5posts = postsService.getLatest5();
        model.addAttribute("latest3posts", latest3posts);
        model.addAttribute("latest5posts", latest5posts);
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/posts")
    public String posts(@PageableDefault(value=4, page=0)Pageable pageable, Model model) {
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
        post.setReader(readerRepository.findByUsername(getPrincipal()));
        postsService.save(post);
        return "redirect:/post/view/" + post.getId();
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable int id, Model model) {
        postsService.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    private String getPrincipal() {
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
