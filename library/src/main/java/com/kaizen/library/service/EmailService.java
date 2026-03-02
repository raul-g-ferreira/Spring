package com.kaizen.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailLoan(String to, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Book Loan Notification: ");
        message.setText("Dear user,\n\n" + title + "\nIs ready to pickup\nLibrary Team");

        mailSender.send(message);
    }


    public void sendEmailReturn(String to, String title) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Book Return Notification: ");
        message.setText("Dear user,\n\n" + title + "\nWas successfully returned\nLibrary Team");

        mailSender.send(message);
    }
}
