package com.devchangetheworld.ewebsite.service.impl;

import com.devchangetheworld.ewebsite.dto.response.ImageResponseDTO;
import com.devchangetheworld.ewebsite.dto.response.ProductResponseDTO;
import com.devchangetheworld.ewebsite.entities.Image;
import com.devchangetheworld.ewebsite.entities.Product;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.mapper.AutoMapperPicture;
import com.devchangetheworld.ewebsite.repository.ImageRepository;
import com.devchangetheworld.ewebsite.repository.ProductRepository;
import com.devchangetheworld.ewebsite.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final AutoMapperPicture autoMapperPicture;
    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("image", "id", id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
                    throw new ResourceNotFoundException("image", "id", id);
                });
    }

    @Override
    public List<ImageResponseDTO> saveImages(Long productId, List<MultipartFile> files) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
        List<ImageResponseDTO> savedImages = new ArrayList<>();

        for(MultipartFile file : files){
            Image savedImage = processAndSaveImage(file, product);
            savedImages.add(autoMapperPicture.toResponse(savedImage));
        }

        return  savedImages;

    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException("image", "id", imageId));
        try {
            updateImageDetails(file, image);
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to update image: " + e.getMessage(), e);
        }


    }

    private Image processAndSaveImage(MultipartFile file, Product product) {
        try {
            Image image = new Image();
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            image.setProduct(product);
            image.setDownloadUrl("temp"); //temp for by pass constraint @NotBlank

            Image savedImage = imageRepository.save(image);
            String downloadUrl = "/api/v1/images/image/download/" + savedImage.getId();
            savedImage.setDownloadUrl(downloadUrl);

            return imageRepository.save(savedImage);
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
    }

    private void updateImageDetails(MultipartFile file, Image image) throws IOException, SQLException {
        image.setFileName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setImage(new SerialBlob(file.getBytes()));
    }
}
