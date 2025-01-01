package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.ProductDTO;
import com.vital_aid_crud_api.service.Interfaces.MedicalStoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/medical_store")
public class MedicalStoreController {

    @Autowired
    private MedicalStoreService medicalStoreService;

                                            //  LIST OF ALL PRODUCTS

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductDTO>> listOfAllProducts() {
        List<ProductDTO> products = medicalStoreService.listOfAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

                                            // ADDING NEW PRODUCT
                        
    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@Valid @RequestPart(value = "productDTO") ProductDTO productDTO,
                                 @RequestPart(value = "file", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()){
            productDTO = medicalStoreService.addProductWithImage(productDTO, file);
        }else{
            productDTO.setProductPhotoUrl(BaseImageUrls.PRODUCT_PHOTO_BASE_URL);
            productDTO = medicalStoreService.addProduct(productDTO);
        }
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

                                            //  UPDATING PRODUCT

    @PutMapping("/updateProduct/{Id}")
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Long Id,@Valid @RequestPart(value = "productDTO", required = false) ProductDTO productDTO,
                                        @RequestPart(value = "file", required = false) MultipartFile file) {
        productDTO = medicalStoreService.updateProductById(Id, productDTO, file);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

                                            //  DELETING PRODUCT

    @DeleteMapping("/deleteProduct/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long Id) {
        medicalStoreService.deleteProduct(Id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
