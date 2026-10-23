package com.example.practiceset.controller;

import com.example.practiceset.entity.User;
import com.example.practiceset.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {

        String result = userService.registerUser(user);

        if (result.equals("email already exists re")) {
            model.addAttribute("error", "Email already registered!");
            return "register";
        }

        return "redirect:/login?registered";
    }

}
