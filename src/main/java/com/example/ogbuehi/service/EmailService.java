package com.example.ogbuehi.service;


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
import java.util.Objects;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(details.getRecipient());
            messageHelper.setText(details.getMsgBody());
            messageHelper.setSubject(details.getSubject());

            FileSystemResource file =
                    new FileSystemResource(new File(details.getAttachment()));
            messageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            mailSender.send(mimeMessage);
            return "Message Sent Successfully!!";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendSimpleMail(EmailDetails details) {
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            mailSender.send(mailMessage);
            return "Message Sent Successfully!!";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
