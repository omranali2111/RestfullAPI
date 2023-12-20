package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.model.Image;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/imagescontent")
public class imageContentController {
    @GetMapping(path = "/{id}")

    public ResponseEntity<byte[]> showImage(@PathVariable String id) throws IOException {

        File file = new File("./Data/Admin.png");
        byte[] fileContent = FileUtils.readFileToByteArray(file);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(fileContent);
    }

    @PostMapping()

    public ResponseEntity<String> UploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        File destinationFile = new File("./Data/"+ file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), destinationFile);
        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());

    }
}
