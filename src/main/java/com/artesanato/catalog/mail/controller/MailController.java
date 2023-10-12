package com.artesanato.catalog.mail.controller;

import com.artesanato.catalog.mail.Email;
import com.artesanato.catalog.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/email")
public class MailController {

    public MailService mailService;

    @Autowired
    public MailController (MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public int print() {
        return 5;
    }

    @PostMapping
    public String sendEmailEndPoint(@RequestBody Email email) {

        try {
            System.out.println(email.getName());
            System.out.println(email.getEmail());
            System.out.println(email.getMsg());

            this.mailService.sendEmail(email);
            return "Success sending email";
        }
        catch (Exception e) {
            return "Failed to send email" + e;
        }
    }

}
