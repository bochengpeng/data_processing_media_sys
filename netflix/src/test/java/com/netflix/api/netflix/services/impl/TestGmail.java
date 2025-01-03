package com.netflix.api.netflix.services.impl;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class TestGmail
{
    public static void main(String[] args)
    {
        final String username = "traanfthuw2504@gmail.com";
        final String password = "ahkh xwli ieuh gvhe";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("traafthuw2504@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
            message.setSubject("Test Mail");
            message.setText("This is a test email.");

            Transport.send(message);
            System.out.println("Email sent successfully");
        } catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
