package com.netflix.api.netflix.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender)
    {
        this.emailSender = emailSender;
    }

    public void sendPasswordResetEmail(String email, String token)
    {
        String resetLink = "http://localhost:8080/netflix/forgot-password?token=" + token;
        String subject = "Password Reset Request";
        String body = "To reset your password, please click the link below:\n\n" + resetLink;

        emailSender.sendEmail(email, subject, body);
    }
}
