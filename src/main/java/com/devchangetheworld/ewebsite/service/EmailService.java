package com.devchangetheworld.ewebsite.service;

public interface EmailService {
    public void sendSimpleEmail(String toEmail, String subject, String body);
}
