package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.payment.PaymentRequestDTO;
import com.devchangetheworld.ewebsite.dto.payment.TransactionStatusResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface VNPayService {
    PaymentRequestDTO createPaymentRequest(HttpServletRequest req, Long orderId) throws UnsupportedEncodingException;

    TransactionStatusResponseDTO handleTransactionResult(String amount, String bankCode, String order, String responseCode);
}
