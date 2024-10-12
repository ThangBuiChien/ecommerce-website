package com.devchangetheworld.ewebsite.dto.request;

import jakarta.persistence.SecondaryTable;
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
