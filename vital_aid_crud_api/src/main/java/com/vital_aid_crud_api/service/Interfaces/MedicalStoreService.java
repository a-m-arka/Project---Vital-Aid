package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Payloads.ProductDTO;

public interface MedicalStoreService {
    List<ProductDTO> listOfAllProducts();
    ProductDTO addProductWithImage(ProductDTO medicalStoreDTO, MultipartFile file);
    ProductDTO addProduct(ProductDTO medicalStoreDTO);
    ProductDTO updateProductById(Long productId, ProductDTO medicalStoreDTO, MultipartFile file);
    void deleteProduct(Long id);
}
