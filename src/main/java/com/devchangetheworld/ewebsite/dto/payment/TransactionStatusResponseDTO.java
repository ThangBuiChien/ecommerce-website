package com.devchangetheworld.ewebsite.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusResponseDTO {
    String status;
    String message;
    Object data;
}
