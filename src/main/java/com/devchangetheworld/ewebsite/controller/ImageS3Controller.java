package com.devchangetheworld.ewebsite.controller;


import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.dto.product.ImageS3ResponseDTO;
import com.devchangetheworld.ewebsite.entities.ImageS3;
import com.devchangetheworld.ewebsite.exception.ResourceNotFoundException;
import com.devchangetheworld.ewebsite.service.ImageS3Service;
import com.devchangetheworld.ewebsite.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images3")
public class ImageS3Controller {
    private final ImageS3Service imageS3Service;
    private final S3Service s3Service;

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveImages(
            @RequestParam List<MultipartFile> files,
            @RequestParam Long productId) {
        try {
            List<ImageS3ResponseDTO> imageDtos = imageS3Service.saveImages(productId, files);
            return ResponseEntity.ok(new ApiResponse(200, "Upload success!", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "Upload failed!", e.getMessage()));
        }
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<?> getImage(@PathVariable Long imageId,
                                      @RequestParam(defaultValue = "false") boolean download) {
        try {
            ImageS3 image = imageS3Service.getImageS3ById(imageId);

            if (download) {
                // Generate a pre-signed URL for downloading
                String presignedUrl = s3Service.generatePresignedUrl(image.getS3Key(), 5); // URL valid for 5 minutes
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + image.getFileName() + "\"")
                        .body(new ApiResponse(200, "Download URL generated", presignedUrl));
            } else {
                // Return the S3 URL for displaying
                return ResponseEntity.ok(new ApiResponse(200, "Image URL", image.getS3Url()));
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(404, "Image not found", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(500, "Error retrieving image", e.getMessage()));
        }
    }
}
