package com.Spring.springboot_email.Service;

import com.Spring.springboot_email.dto.EmailData;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.FileSystem;

@Service
public class EmailService
{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public String sendEmail(EmailData emailData){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderEmail);
        simpleMailMessage.setTo(emailData.getToEmail());
        simpleMailMessage.setSubject(emailData.getSubject());
        simpleMailMessage.setText(emailData.getBody());
        try {
            javaMailSender.send(simpleMailMessage);
            return "Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email: " + e.getMessage();
        }

    }

    public String sendEmailWithAttachment(EmailData emailData) throws MessagingException {
        // This method can be implemented using JavaMailSender's MimeMessage
        MimeMessage mimeMessage= javaMailSender.createMimeMessage();
        // and MimeMessageHelper classes to add attachments.
        MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage,true);
        try {
            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(emailData.getToEmail());
            messageHelper.setSubject(emailData.getSubject());
            messageHelper.setText(emailData.getBody());
            // Add attachment
            FileSystemResource file = new FileSystemResource(new File(emailData.getAttachmentPath()));
            if (file.exists()) {
                messageHelper.addAttachment(file.getFilename(), file);
            } else {
                return "Attachment file not found: " + emailData.getAttachmentPath();
            }
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email with attachment: " + e.getMessage();
        }
        return "Email with attachment sent successfully"; // Placeholder return statement
    }
}
