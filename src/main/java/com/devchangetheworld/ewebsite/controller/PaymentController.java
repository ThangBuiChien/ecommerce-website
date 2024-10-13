package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.request.PaymentRequestDTO;
import com.devchangetheworld.ewebsite.dto.response.TransactionStatusResponseDTO;
import com.devchangetheworld.ewebsite.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/payment")
public class PaymentController {

    private final VNPayService vnPayService;

    @GetMapping("/create_payment/{orderId}")
    public ResponseEntity<?> createPayment(HttpServletRequest req, @PathVariable Long orderId) throws UnsupportedEncodingException {
        PaymentRequestDTO paymentRequestDTO = vnPayService.createPaymentRequest(req, orderId);

        return ResponseEntity.ok().body(paymentRequestDTO);
    }

    @GetMapping("/payment_info")
    public ResponseEntity<?> transaction(
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_BankCode") String bankCode,
            @RequestParam(value = "vnp_OrderInfo") String order,
            @RequestParam(value = "vnp_ResponseCode") String responseCode) {
        TransactionStatusResponseDTO transactionStatusDTO = vnPayService.handleTransactionResult(amount
                , bankCode, order, responseCode);

        return ResponseEntity.status(HttpStatus.OK).body(transactionStatusDTO);
    }
}
