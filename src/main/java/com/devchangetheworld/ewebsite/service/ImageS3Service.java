package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.response.ImageS3ResponseDTO;
import com.devchangetheworld.ewebsite.entities.ImageS3;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageS3Service {
    public ImageS3 getImageS3ById(Long id);
    public List<ImageS3ResponseDTO> saveImages(Long productId, List<MultipartFile> files);
    public void updateImage(MultipartFile file, Long imageId);
}
