package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.service.ImageContentService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.tika.metadata.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController

@RequestMapping("/api/v1/imagescontent")
public class imageContentController {
    @Autowired
   private ImageContentService imageContentService;
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable String id) throws IOException {
        File file = imageContentService.retrieveImageFile(id);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileContent = FileUtils.readFileToByteArray(file);
        MediaType mediaType = imageContentService.getMediaType(file);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .body(fileContent);
    }



    @PostMapping()
    public ResponseEntity<String> UploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Image uploadedImage = imageContentService.uploadImage(file);
            return ResponseEntity.ok("File uploaded successfully: " + uploadedImage.getPath());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        try {
            imageContentService.UpdateImage(id,file);
            return ResponseEntity.ok("File updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating file");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteImage(@PathVariable int id) {
        try {
            imageContentService.deleteImage(id);
            return ResponseEntity.ok("File and database record deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during file deletion");
        }
    }

}
