package com.netflix.api.netflix.controllers;

import com.netflix.api.netflix.services.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController
{

    private final EmailSender emailSender;

    @Autowired
    public EmailController(EmailSender emailSender)
    {
        this.emailSender = emailSender;
    }

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body)
    {
        emailSender.sendEmail(to, subject, body);
        return "Email sent successfully to " + to;
    }
}
