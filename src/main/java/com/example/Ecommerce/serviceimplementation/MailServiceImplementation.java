package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.service.MailService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class MailServiceImplementation implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("ecomservice07@gmail.com"); // your configured email
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }
}
