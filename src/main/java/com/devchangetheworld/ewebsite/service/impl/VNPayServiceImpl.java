package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.config.Config;
import com.devchangetheworld.ewebsite.dto.payment.PaymentRequestDTO;
import com.devchangetheworld.ewebsite.dto.payment.TransactionStatusResponseDTO;
import com.devchangetheworld.ewebsite.entities.Order;
import com.devchangetheworld.ewebsite.enums.OrderStatus;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoOrderMapper;
import com.devchangetheworld.ewebsite.repository.OrderRepository;
import com.devchangetheworld.ewebsite.service.EmailService;
import com.devchangetheworld.ewebsite.service.VNPayService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VNPayServiceImpl implements VNPayService {

    private final OrderRepository orderRepository;
    private final AutoOrderMapper autoOrderMapper;
    private final EmailService emailService;
    @Transactional
    @Override
    public PaymentRequestDTO createPaymentRequest(HttpServletRequest req, Long orderId) throws UnsupportedEncodingException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order", "id", orderId));
        if(order.getOrderStatus() != OrderStatus.PENDING){
            throw new RuntimeException("Order has been pay!");
        }

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String bankCode = req.getParameter("bankCode");
//        long amount = 1000000;

        long amount = order.getTotalAmount().multiply(BigDecimal.valueOf(100)).longValue();
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
//        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", Config.getIpAddress(req));

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setStatus("OK");
        paymentRequestDTO.setMessage("Success");
        paymentRequestDTO.setUrl(paymentUrl);

        order.setPayId(Long.parseLong(vnp_TxnRef));
        orderRepository.save(order);
        return paymentRequestDTO;
    }

    @Transactional
    @Override
    public TransactionStatusResponseDTO handleTransactionResult(String amount, String bankCode,
                                                                String order, String responseCode) {
        TransactionStatusResponseDTO transactionStatusDTO = new TransactionStatusResponseDTO();
        if (responseCode.equals("00")) {
            String decodedOrderInfo = java.net.URLDecoder.decode(order, StandardCharsets.UTF_8);
            String orderIdStr = decodedOrderInfo.substring(decodedOrderInfo.indexOf(":") + 1);
            Long orderId = Long.parseLong(orderIdStr);
            Order order1  = orderRepository.findByPayId(orderId)
                            .orElseThrow(() -> new ResourceNotFoundException("order", "payId", orderId));
            order1.setOrderStatus(OrderStatus.PAID);
            orderRepository.save(order1);

            try {
                //send html email
                emailService.sendHtmlFormatSuccessPayment(order1.getUser().getEmail(), "Payment success",
                        order1.getUser().getFirstName() + " " + order1.getUser().getLastName(),
                        order1.getTotalAmount().toString(), order1.getId().toString());
            }
            catch (MessagingException | IOException e) {
                emailService.sendSimpleEmail(order1.getUser().getEmail(), "Payment success but error when send mail",
                        "Do not worry:)");
            }


            transactionStatusDTO.setStatus("Ok");
            transactionStatusDTO.setMessage("Successfully");
            transactionStatusDTO.setData(autoOrderMapper.toResponseDTO(order1));
        } else {
            transactionStatusDTO.setStatus("No");
            transactionStatusDTO.setMessage("Failed");
            transactionStatusDTO.setData("");
        }
        return transactionStatusDTO;
    }
}
