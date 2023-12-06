package cn.edu.ynu.demo_app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    // ctor
    public EmailService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
        System.out.println(javaMailSender.getClass().getName());
    }
    public void sendEmail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }
}
