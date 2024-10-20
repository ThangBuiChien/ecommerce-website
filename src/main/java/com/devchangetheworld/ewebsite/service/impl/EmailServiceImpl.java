package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String setFrom;

    @Async
    @Override
    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

    }

    @Async
    @Override
    public void sendHtmlFormatSuccessPayment(String toEmail, String subject,  String customerName, String amount, String orderId) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(setFrom);
        helper.setTo(toEmail);
        helper.setSubject(subject);

        // Load HTML template
        ClassPathResource resource = new ClassPathResource("static/payment-confirmation-template.html");
        String htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        // Replace placeholders with actual values
        String htmlContent = htmlTemplate
                .replace("${customerName}", customerName)
                .replace("${amount}", amount)
                .replace("${orderId}", orderId);

        // Set the HTML content
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
