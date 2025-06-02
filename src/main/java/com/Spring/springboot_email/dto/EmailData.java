package com.Spring.springboot_email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailData {

    private String toEmail;
    private String subject;
    private String body;
    private String attachmentPath; // Optional, for sending attachments
}
