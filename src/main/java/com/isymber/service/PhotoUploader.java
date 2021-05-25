package com.isymber.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.isymber.model.Photo;
import com.isymber.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class PhotoUploader {
    private final Cloudinary cloudinary;
    private final PhotoRepo photoRepo;

    @Autowired
    public PhotoUploader(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "galleryappvaadin",
                "api_key", "439427514275794",
                "api_secret", "HPnU_yEuAB3AxegPfDMVpn0eKbE"));
    }

    private String uploadPhoto(String path){
        File file = new File(path);
        Map uploadResult = null;
        try {
            System.out.println(path);
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e) {
//            e.printStackTrace();
        }
        assert uploadResult != null;
        return uploadResult.get("url").toString();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void savePhoto(){
        String path = "C:\\Users\\Kamil\\Downloads\\enego.png";
        photoRepo.save(new Photo(uploadPhoto(path)));
    }
}
