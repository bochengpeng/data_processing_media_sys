package com.netflix.api.netflix.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender
{

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("traanfthuw2504@gmail.com"); // Optional, can be set globally
        mailSender.send(message);
    }
}

