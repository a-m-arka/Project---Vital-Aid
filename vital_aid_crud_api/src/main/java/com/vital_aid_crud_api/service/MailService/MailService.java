package com.vital_aid_crud_api.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendOtpEmail(String email, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("vitalaid2104@gmail.com"); // Set the sender's email
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
            System.out.println("Email sent successfully to " + email);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            throw new IllegalStateException("Failed to send email.", e);
        }
    }
}
