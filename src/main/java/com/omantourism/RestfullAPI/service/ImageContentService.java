package com.omantourism.RestfullAPI.service;

import com.omantourism.RestfullAPI.model.Image;
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
    static public int IdCounter=0;

    public ResponseEntity<byte[]> showImage(@PathVariable String id) throws IOException {
        String[] possibleExtensions = {"png", "jpg", "jpeg"};

        Optional<File> fileOptional = Arrays.stream(possibleExtensions)
                .map(ext -> Paths.get("./Data/image_" + id + "." + ext).toFile())
                .filter(File::exists)
                .findFirst();

        if (!fileOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        File file = fileOptional.get();
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        MediaType mediaType = getMediaType(file);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getName() + "\"")
                .body(fileContent);
    }
    private MediaType getMediaType(File file) {
        MimeTypes mimeTypes = TikaConfig.getDefaultConfig().getMimeRepository();
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            org.apache.tika.mime.MediaType tikaMediaType = mimeTypes.detect(input, new Metadata());
            return MediaType.parseMediaType(tikaMediaType.toString());
        } catch (IOException e) {
            System.out.println("Error detecting MIME type: " + e.getMessage());
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public ResponseEntity<String> UploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        synchronized (this) {
            IdCounter++; // Increment the counter
        }


        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = "image_" + IdCounter+"."+extension;
        File destinationFile = new File("./Data/"+newFileName );
        FileUtils.copyInputStreamToFile(file.getInputStream(), destinationFile);


        Image imageInfo = new Image();
        imageInfo.setId(String.valueOf(IdCounter));
        imageInfo.setPath(destinationFile.getPath());
        ImageInfoService.createImage(imageInfo);
        return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
    }

    public ResponseEntity<String> UpdateImage(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {

        String[] possibleExtensions = {"png", "jpg", "jpeg"};


        Arrays.stream(possibleExtensions)
                .map(ext -> new File("./Data/image_" + id + "." + ext))
                .filter(File::exists)
                .forEach(File::delete);


        String newExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        File destinationFile = new File("./Data/image_" + id + "." + newExtension);
        FileUtils.copyInputStreamToFile(file.getInputStream(), destinationFile);

        return ResponseEntity.ok("File updated successfully: " + destinationFile.getName());
    }
    public ResponseEntity<String> DeleteImage(@PathVariable String id){
        String[] possibleExtensions = {"png", "jpg", "jpeg"};


        File fileToDelete = Arrays.stream(possibleExtensions)
                .map(ext -> new File("./Data/image_" + id + "." + ext))
                .filter(File::exists)
                .findFirst()
                .orElse(null);
        boolean deleted = fileToDelete.delete();
        if (deleted) {
            return ResponseEntity.ok("File deleted successfully: " + fileToDelete.getName());
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete file: " + fileToDelete.getName());
        }

    }
}
