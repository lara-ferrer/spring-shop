package com.spring.shop.controller;

import com.spring.shop.domain.User;
import com.spring.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user, Model model) {
        // Dar de alta el usuario
        userService.addUser(user);
        model.addAttribute("message", "Usuario registrado correctamente");
        return "register";
    }

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/user/{userId}")
    public String getUserProfile(Model model, @PathVariable long userId) {
        return "profile";
    }

    @GetMapping("/user/{userId}/orders")
    public String getUserOrders(Model model, @PathVariable long userId) {
        return "orders";
    }
}

