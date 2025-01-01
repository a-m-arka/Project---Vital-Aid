package com.vital_aid_crud_api.web.ImageUploaderController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.service.Interfaces.UserService;

@RestController
@RequestMapping("/vital_aid/uploadImage")
public class ImageUploaderController {

    @Autowired
    private UserService userService;

    @PutMapping("/userProfilePhoto")
    public ResponseEntity<String> uploadUserProfilePhoto(@RequestParam("file") MultipartFile file){
        userService.updateLoggedInUserProfileImage(file);
        return ResponseEntity.ok("Image uploaded successfully");
    }
}
