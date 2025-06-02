package com.Spring.springboot_email.Controller;

import com.Spring.springboot_email.Service.EmailService;
import com.Spring.springboot_email.dto.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailData emailData) {
        return emailService.sendEmail(emailData);
    }

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(@RequestBody EmailData emailData) {
        try {
            return emailService.sendEmailWithAttachment(emailData);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email with attachment: " + e.getMessage();
        }
    }
}
