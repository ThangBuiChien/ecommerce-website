package com.devchangetheworld.ewebsite.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageResponseDTO {
    private Long id;
    private String fileName;
    private String downloadUrl;
}
