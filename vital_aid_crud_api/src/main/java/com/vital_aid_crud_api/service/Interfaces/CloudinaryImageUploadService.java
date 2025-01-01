package com.vital_aid_crud_api.service.Interfaces;


import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageUploadService {
    public String uploadImageToCloud(MultipartFile file, String folderName);
    public void deleteImageFromCloud(String publicId);
    public String extractPublicIdFromUrl(String doctorPhotoUrl);
}
