package com.devchangetheworld.ewebsite.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageS3ResponseDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private String s3Key;
    private String s3Url;
}
