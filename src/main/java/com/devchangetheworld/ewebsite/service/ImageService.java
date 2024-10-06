package com.devchangetheworld.ewebsite.service;

import com.devchangetheworld.ewebsite.dto.response.ImageResponseDTO;
import com.devchangetheworld.ewebsite.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageResponseDTO> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file,  Long imageId);
}
