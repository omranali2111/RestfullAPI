package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.Image;
import com.omantourism.RestfullAPI.repository.PhotoInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ImageContentService {
    @Autowired
    private imageInfoService ImageInfoService;
    @Autowired
    private PhotoInfoRepository photoInfoRepository;


    public File retrieveImageFile(String id) {
        String[] possibleExtensions = {"png", "jpg", "jpeg"};

        return Arrays.stream(possibleExtensions)
                .map(ext -> Paths.get("./Data/image_" + id + "." + ext).toFile())
                .filter(File::exists)
                .findFirst()
                .orElse(null);
    }
    public MediaType getMediaType(File file) {
        MimeTypes mimeTypes = TikaConfig.getDefaultConfig().getMimeRepository();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            org.apache.tika.mime.MediaType tikaMediaType = mimeTypes.detect(input, new Metadata());
            return MediaType.parseMediaType(tikaMediaType.toString());
        } catch (IOException e) {
            System.out.println("Error detecting MIME type: " + e.getMessage());
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public Image uploadImage(MultipartFile file) throws IOException {
        Image imageInfo = new Image();
        imageInfo = photoInfoRepository.save(imageInfo);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = "image_" + imageInfo.getId() + "." + extension;
        File destinationFile = new File("./Data/" + newFileName);
        FileUtils.copyInputStreamToFile(file.getInputStream(), destinationFile);

        imageInfo.setPath(destinationFile.getPath());
        return photoInfoRepository.save(imageInfo);
    }

    public void UpdateImage(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {

        String[] possibleExtensions = {"png", "jpg", "jpeg"};


        Arrays.stream(possibleExtensions)
                .map(ext -> new File("./Data/image_" + id + "." + ext))
                .filter(File::exists)
                .forEach(File::delete);


        String newExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        File destinationFile = new File("./Data/image_" + id + "." + newExtension);
        FileUtils.copyInputStreamToFile(file.getInputStream(), destinationFile);


    }

    public void deleteImage(int id) throws IOException {
        String[] possibleExtensions = {"png", "jpg", "jpeg"};
        boolean fileDeleted = Arrays.stream(possibleExtensions)
                .map(ext -> new File("./Data/image_" + id + "." + ext))
                .filter(File::exists)
                .findFirst()
                .map(File::delete)
                .orElse(false);

        if (photoInfoRepository.existsById(id)) {
            photoInfoRepository.deleteById(id);
        } else {
            if (!fileDeleted) {
                throw new EntityNotFoundException("Image not found with ID: " + id);
            }
        }
    }
}
