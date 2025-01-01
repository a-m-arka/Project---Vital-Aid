package com.vital_aid_crud_api.Config;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Cloudinary getCloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dgs8jcafi");
        config.put("api_key",
                        "546896336581665");
        config.put("api_secret", "3HxP6AjGdjR_Ztq-8n_dupcHaIA");
        config.put("secure", "true");

        return new Cloudinary(config);
    }
}
