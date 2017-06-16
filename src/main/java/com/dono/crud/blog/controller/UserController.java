package com.dono.crud.blog.controller;

import com.dono.crud.blog.model.Reader;
import com.dono.crud.blog.validation.ReaderForm;
import com.dono.crud.blog.model.UserType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class UserController extends MyAbstractController {

    @GetMapping("/users")
    public String users(Model model) {
        List<Reader> users = readerService.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/new")
    public String newUser(Model model) {
        ReaderForm readerForm = new ReaderForm();
        model.addAttribute("readerForm", readerForm);
        return "userForm";
    }

    @PostMapping("user/new")
    public String newUser(@Valid ReaderForm readerForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            readerForm.setValid(false);
            return "userForm";
        }

        Reader reader = new Reader();
        reader.setUsername(readerForm.getUsername());
        reader.setPassword(readerForm.getPassword());
        reader.setUserCreatedDate(LocalDate.now());
        reader.setUserType(UserType.READER);

        readerService.save(reader);
        model.addAttribute("success", "User '" + readerForm.getUsername() + "' successfully registered.");
        model.addAttribute("registered", true);
        return "login";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("user/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        readerService.delete(readerService.getOne(username));
        return "redirect:/users";
    }

}
