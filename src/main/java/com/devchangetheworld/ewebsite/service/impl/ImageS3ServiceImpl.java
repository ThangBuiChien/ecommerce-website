//package com.devchangetheworld.ewebsite.service.impl;
//
//import com.devchangetheworld.ewebsite.dto.product.ImageS3ResponseDTO;
//import com.devchangetheworld.ewebsite.entities.ImageS3;
//import com.devchangetheworld.ewebsite.entities.Product;
//import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
//import com.devchangetheworld.ewebsite.mapper.AutoMapperImageS3;
//import com.devchangetheworld.ewebsite.repository.ImageS3Repository;
//import com.devchangetheworld.ewebsite.repository.ProductRepository;
//import com.devchangetheworld.ewebsite.service.ImageS3Service;
//import com.devchangetheworld.ewebsite.service.S3Service;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class ImageS3ServiceImpl implements ImageS3Service {
//
//    private final ImageS3Repository imageS3Repository;
//    private final ProductRepository productRepository;
//    private final S3Service s3Service;
//    private final AutoMapperImageS3 autoMapperImageS3;
//
//    @Override
//    public ImageS3 getImageS3ById(Long id) {
//        return imageS3Repository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("image", "id", id));
//    }
//
//    @Override
//    public List<ImageS3ResponseDTO> saveImages(Long productId, List<MultipartFile> files) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
//        List<ImageS3ResponseDTO> savedImages = new ArrayList<>();
//
//        for(MultipartFile file : files){
//            ImageS3 savedImage = processAndSaveImage(file, product);
//            savedImages.add(autoMapperImageS3.toResponse(savedImage));
//        }
//
//        return savedImages;
//    }
//
//    @Override
//    public void updateImage(MultipartFile file, Long imageId) {
//        ImageS3 image = imageS3Repository.findById(imageId)
//                .orElseThrow(() -> new ResourceNotFoundException("image", "id", imageId));
//
//        // Delete old image from S3
//        if (image.getS3Key() != null) {
//            s3Service.deleteFile(image.getS3Key());
//        }
//
//        updateImageDetails(file, image);
//        imageS3Repository.save(image);
//
//    }
//
//    private ImageS3 processAndSaveImage(MultipartFile file, Product product) {
//        try {
//            String s3Url = s3Service.uploadFile(file);
//            String s3Key = extractS3KeyFromUrl(s3Url);
//
//            ImageS3 image = new ImageS3();
//            image.setFileName(file.getOriginalFilename());
//            image.setFileType(file.getContentType());
//            image.setS3Key(s3Key);
//            image.setS3Url(s3Url);
//            image.setProduct(product);
//
//            return imageS3Repository.save(image);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
//        }
//    }
//
//    private void updateImageDetails(MultipartFile file, ImageS3 image) {
//        String s3Url = s3Service.uploadFile(file);
//        String s3Key = extractS3KeyFromUrl(s3Url);
//
//        image.setFileName(file.getOriginalFilename());
//        image.setFileType(file.getContentType());
//        image.setS3Key(s3Key);
//        image.setS3Url(s3Url);
//    }
//
//    private String extractS3KeyFromUrl(String s3Url) {
//        // Extract the key from the S3 URL
//        return s3Url.substring(s3Url.lastIndexOf("/") + 1);
//    }
//}
