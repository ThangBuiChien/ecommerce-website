package com.devchangetheworld.ewebsite.controller;

import com.devchangetheworld.ewebsite.dto.api_response.ApiResponse;
import com.devchangetheworld.ewebsite.dto.product.ImageResponseDTO;
import com.devchangetheworld.ewebsite.entities.Image;
import com.devchangetheworld.ewebsite.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageResponseDTO> imageDtos = imageService.saveImages(productId, files);
            return ResponseEntity.ok(new ApiResponse(200, "Upload success!", imageDtos));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(500, "Upload failed!", e.getMessage()));
        }

    }

    //To display instead of downloading, just change header of ResponseEntity
    @GetMapping("/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return  ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName() + "\"")
                .body(resource);
    }

}
