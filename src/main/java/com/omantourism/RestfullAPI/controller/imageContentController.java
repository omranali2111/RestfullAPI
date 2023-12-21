package com.omantourism.RestfullAPI.controller;

import com.omantourism.RestfullAPI.service.ImageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

@RequestMapping("/api/v1/imagescontent")
public class imageContentController {
    @Autowired
   private ImageContentService imageContentService;
    @GetMapping(path = "/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable String id) throws IOException {
      return imageContentService.showImage(id);
    }

    @PostMapping()

    public ResponseEntity<String> UploadImage(@RequestParam("file") MultipartFile file) throws IOException {

       return imageContentService.UploadImage(file);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<String> UpdateImage(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {

        return imageContentService.UpdateImage(id,file);
    }

    @DeleteMapping(path = "/{id}")
public ResponseEntity<String> DeleteImage(@PathVariable String id){

        return imageContentService.DeleteImage(id);

    }

}
