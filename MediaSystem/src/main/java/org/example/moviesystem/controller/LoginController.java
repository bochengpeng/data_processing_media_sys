package org.example.moviesystem.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(HttpSession session)
    {
        if (session.getAttribute("user") != null) {

            return "redirect:/home";
        }

        return "login"; // Refers to login.html in src/main/resources/templates
    }
}

