package org.example.moviesystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        // If the user is already logged in, redirect to home
        if (session.getAttribute("loggedIn") != null && (boolean) session.getAttribute("loggedIn")) {
            return "redirect:/home"; // Redirect to home if already logged in
        }

        return "login"; // Refers to login.html in src/main/resources/templates
    }
}

