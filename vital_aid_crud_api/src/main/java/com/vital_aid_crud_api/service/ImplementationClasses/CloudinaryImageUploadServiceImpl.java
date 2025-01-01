package com.vital_aid_crud_api.service.ImplementationClasses;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;

import jakarta.transaction.Transactional;

@Service
public class CloudinaryImageUploadServiceImpl implements CloudinaryImageUploadService {

    @Autowired
    private Cloudinary cloudinary;

                                        // UPLOAD IMAGE TO CLOUDINARY

    @Transactional
    @Override
    public String uploadImageToCloud(MultipartFile file, String folderName) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", folderName));
            return (String) uploadResult.get("url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

                                        // DELETE IMAGE FROM CLOUDINARY

    @Transactional
    @Override
    public void deleteImageFromCloud(String publicId) {
        if (publicId == null || publicId.trim().isEmpty()) {
            System.out.println("No public ID provided, skipping image deletion.");
            return;
        }
    
        try {
            Map response = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            System.out.println("Cloudinary response: " + response);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
    }
                                            
                                        // EXTRACT PUBLIC ID FROM CLOUDINARY URL
                                        
    public String extractPublicIdFromUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        
        String[] parts = url.split("/image/upload/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid Cloudinary URL");
        }
        
        String fullPath = parts[1];
        // Remove the version part (e.g., v1735211529/)
        int slashIndex = fullPath.indexOf('/');
        if (slashIndex != -1) {
            fullPath = fullPath.substring(slashIndex + 1);
        }
        
        // Remove the file extension (e.g., .png, .jpg)
        int dotIndex = fullPath.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new IllegalArgumentException("Invalid Cloudinary URL - no extension found");
        }
    
        return fullPath.substring(0, dotIndex);
    }
}
