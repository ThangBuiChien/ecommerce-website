package com.devchangetheworld.ewebsite.service;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {
    public void sendSimpleEmail(String toEmail, String subject, String body);

    public void sendHtmlFormatSuccessPayment(String toEmail, String subject, String customerName,
                                             String amount, String orderId) throws MessagingException, IOException;
}
