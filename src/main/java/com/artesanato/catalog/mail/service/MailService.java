package com.artesanato.catalog.mail.service;

import com.artesanato.catalog.mail.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailService extends Session {


    @Autowired
    private JavaMailSender javaMailSender;

    //private final JavaMailSender javaMailSender;

    /*
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(Email email) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email.getEmail());
        message.setTo("realwiikie@hotmail.com");
        message.setText(email.getMsg());

        this.javaMailSender.send(message);
    }
    */

    public void sendEmail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email.getEmail());
        message.setSubject("Sent by: " + email.getName());
        message.setText(email.getMsg());

        System.out.println("Email was sent: " + message);
    }


}
