package com.devchangetheworld.ewebsite.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentRequestDTO implements Serializable {
    private String status;
    private String message;
    private String url;
}
