package com.omantourism.RestfullAPI;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/images")
public class generateImage {
    ArrayList<Image> images=new ArrayList<>();
    @GetMapping
    public ArrayList<Image> getAllImages(){
        return images;
    }
    @GetMapping("/{id}")
    public Image getImageById(@PathVariable String id){
        return images.stream()
                .filter(image -> image.getId().equals(id))
                .findFirst()
                .orElse(null);

    }
    @PostMapping
    public void createImage(@RequestBody Image img){
       if(images.add(img)){
           System.out.println("Image Added ");
       }
       else{
           System.out.println("Image Failed to be Added");
       }

    }
    @PutMapping("/{id}")
    public void UpdateImage(@PathVariable String id,@RequestBody Image img){
       Image newImage= getImageById(id);

       img.description=newImage.description;
       img.path=newImage.path;
    }
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable String id){
        Image newImage= getImageById(id);
        if(images.remove(newImage)){
            System.out.println("Image deleted");
        }
        else{
            System.out.println("Image filed to be deleted");
        }
    }
}
