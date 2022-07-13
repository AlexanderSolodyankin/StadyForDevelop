package com.example.stady.service.impl;

import com.example.stady.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Slf4j
@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    public Boolean send(String toEmail, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject(subject, "UTF-8");

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
            mimeMessageHelper.setFrom(environment.getProperty("spring.mail.username"));
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            log.error("MailService.send: " + e.getMessage());
            return false;
        }
    }
}
