package com.devchangetheworld.ewebsite.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    public String uploadFile(MultipartFile file);
    public void deleteFile(String s3Key);

    public String generatePresignedUrl(String objectKey, int expirationInMinutes);
}
