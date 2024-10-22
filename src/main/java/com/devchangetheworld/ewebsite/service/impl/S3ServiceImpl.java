//package com.devchangetheworld.ewebsite.service.impl;
//
//import com.amazonaws.HttpMethod;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.devchangetheworld.ewebsite.service.S3Service;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.Date;
//import java.util.UUID;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class S3ServiceImpl implements S3Service {
//
//    @Value("${aws.s3.bucket}")
//    private String bucketName;
//
//    @Autowired
//    private AmazonS3 s3Client;
//
//    @Override
//    public String uploadFile(MultipartFile file) {
//        String fileName = generateUniqueFileName(file.getOriginalFilename());
//        try {
//            ObjectMetadata metadata = new ObjectMetadata();
//            metadata.setContentType(file.getContentType());
//            metadata.setContentLength(file.getSize());
//
//            s3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
//
//            return s3Client.getUrl(bucketName, fileName).toString();
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to upload file to S3", e);
//        }
//    }
//
//    @Override
//    public void deleteFile(String s3Key) {
//        s3Client.deleteObject(bucketName, s3Key);
//    }
//
//    @Override
//    public String generatePresignedUrl(String objectKey, int expirationInMinutes) {
//        Date expiration = new Date();
//        long expTimeMillis = expiration.getTime();
//        expTimeMillis += 1000 * 60 * expirationInMinutes;
//        expiration.setTime(expTimeMillis);
//
//        GeneratePresignedUrlRequest generatePresignedUrlRequest =
//                new GeneratePresignedUrlRequest(bucketName, objectKey)
//                        .withMethod(HttpMethod.GET)
//                        .withExpiration(expiration);
//        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
//        return url.toString();
//    }
//
//    private String generateUniqueFileName(String originalFilename) {
//        return UUID.randomUUID().toString() + "-" + originalFilename;
//    }
//}
