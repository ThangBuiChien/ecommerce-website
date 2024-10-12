package com.devchangetheworld.ewebsite.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionStatusResponseDTO {
    String status;
    String message;
    String data;
}
