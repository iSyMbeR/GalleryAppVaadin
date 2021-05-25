package com.isymber.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.isymber.component.InitializeData;
import com.isymber.model.CloudinaryCredentials;
import com.isymber.model.Photo;
import com.isymber.repository.CloudinaryRepo;
import com.isymber.repository.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
public class PhotoUploader {
    private final Cloudinary cloudinary;
    private final PhotoRepo photoRepo;

    @Autowired
    public PhotoUploader(PhotoRepo photoRepo, CloudinaryRepo cloudinaryRepo, InitializeData initializeData) {
        initializeData.loadData();
        Optional<CloudinaryCredentials> cloudinaryCredentials = cloudinaryRepo.findById(1L);
        this.photoRepo = photoRepo;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryCredentials.get().getCloud_name(),
                "api_key", cloudinaryCredentials.get().getApi_key(),
                "api_secret", cloudinaryCredentials.get().getApi_secret()));
    }

    private String uploadPhoto(String path) {
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

    public void savePhoto(String path) {
        photoRepo.save(new Photo(uploadPhoto(path)));
    }
}
