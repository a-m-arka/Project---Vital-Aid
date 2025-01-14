package com.vital_aid_crud_api.service.ImplementationClasses;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vital_aid_crud_api.Entity.Admin;
import com.vital_aid_crud_api.Entity.Product;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.ImageURLs.BaseImageUrls;
import com.vital_aid_crud_api.Payloads.ProductDTO;
import com.vital_aid_crud_api.Util.ApiResponse;
import com.vital_aid_crud_api.repository.AdminRepository;
import com.vital_aid_crud_api.repository.ProductRepository;
import com.vital_aid_crud_api.service.Interfaces.CloudinaryImageUploadService;
import com.vital_aid_crud_api.service.Interfaces.MedicalStoreService;

import java.util.List;
import jakarta.transaction.Transactional;

@Service
public class MedicalStoreServiceImpl implements MedicalStoreService{

    @Autowired
    private ProductRepository medicalStoreRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ModelMapper productMapper;

    @Autowired
    private CloudinaryImageUploadService imageUploadService;

                                    // LIST OF ALL PRODUCTS

    @Transactional                                
    @Override
    public List<ProductDTO> listOfAllProducts(){
        List<Product> allProducts = medicalStoreRepository.findAll();
        return allProducts.stream().map(this::convertToDTO).toList();
    }

                                    // ADD PRODUCT WITH IMAGE

    @Transactional
    @Override
    public ProductDTO addProductWithImage(ProductDTO productDTODTO, MultipartFile file){
        
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin storeManagingAdmin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String productPhotoUrl = imageUploadService.uploadImageToCloud(file, "vital_aid/products");
        productDTODTO.setProductPhotoUrl(productPhotoUrl);

        Product newProduct = convertToEntity(productDTODTO);
        newProduct.setStoreManagedBy(storeManagingAdmin);

        newProduct = medicalStoreRepository.save(newProduct);
        return convertToDTO(newProduct);
    }

                                    // ADD PRODUCT WITHOUT IMAGE

    @Transactional
    @Override
    public ProductDTO addProduct(ProductDTO productDTO){
                                        
    String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
    Admin storeManagingAdmin = adminRepository.findByPersonEmail(adminEmail)
            .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));
                                        
                                        
    Product newProduct = convertToEntity(productDTO);
    newProduct.setStoreManagedBy(storeManagingAdmin);
                                
    newProduct = medicalStoreRepository.save(newProduct);
    return convertToDTO(newProduct);
    }

                                    // UPDATE PRODUCT

    @Transactional
    @Override
    public ProductDTO updateProductById(Long productId, ProductDTO productDTO, MultipartFile file){
        Product product = medicalStoreRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Store", "id", productId));
        String adminEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin storeManagingAdmin = adminRepository.findByPersonEmail(adminEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", adminEmail));

        String productCurrentPhotoUrl = product.getProductPhotoUrl();
        if(productCurrentPhotoUrl != null && !productCurrentPhotoUrl.equals(BaseImageUrls.PRODUCT_PHOTO_BASE_URL)){
            String publicId = imageUploadService.extractPublicIdFromUrl(productCurrentPhotoUrl);
            if(publicId != null){
                try{
                    imageUploadService.deleteImageFromCloud(publicId);
                }catch(Exception e){
                    ApiResponse response = new ApiResponse("Failed to delete image from cloud", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }

        int productCurrentStock = product.getProductStockQuantity();
        int productNewStock = productDTO.getProductStockQuantity();
        if(productCurrentStock != productNewStock){
            product.setProductStockQuantity(productNewStock);
        }
        
        product.setProductName(productDTO.getProductName());
        product.setProductCategory(productDTO.getProductCategory());
        product.setProductPrice(productDTO.getProductPrice());

        String productPhotoUrl = imageUploadService.uploadImageToCloud(file, "vital_aid/products");
        product.setProductPhotoUrl(productPhotoUrl);
        product.setStoreManagedBy(storeManagingAdmin);

        product = medicalStoreRepository.save(product);
        return convertToDTO(product);
    }

                                    // DELETE PRODUCT
    
    @Transactional
    @Override
    public void deleteProduct(Long id){
        Product medicalStore = medicalStoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Store", "id", id));

        String productCurrentPhotoUrl = medicalStore.getProductPhotoUrl();
        if(productCurrentPhotoUrl != null && !productCurrentPhotoUrl.equals(BaseImageUrls.PRODUCT_PHOTO_BASE_URL)){
            String publicId = imageUploadService.extractPublicIdFromUrl(productCurrentPhotoUrl);
            if(publicId != null){
                try{
                    imageUploadService.deleteImageFromCloud(publicId);
                }catch(Exception e){
                    ApiResponse response = new ApiResponse("Failed to delete image from cloud", false);
                    throw new IllegalStateException(response.getMessage());
                }
            }
        }

        Admin storeManagingAdmin = medicalStore.getStoreManagedBy();
        if(storeManagingAdmin != null){
            storeManagingAdmin.getMedicalStoreProducts().remove(medicalStore);
            adminRepository.save(storeManagingAdmin);
        }
        medicalStoreRepository.delete(medicalStore);
    }



                                    // HELPER METHOD TO CONVERT ENTITY TO DTO
    private ProductDTO convertToDTO(Product product){
        ProductDTO productDTO = productMapper.map(product, ProductDTO.class);
        return productDTO;
    }

                                    // HELPER METHOD TO CONVERT DTO TO ENTITY
    private Product convertToEntity(ProductDTO productDTO){
        Product product = productMapper.map(productDTO, Product.class);
        return product;
    }                         

}
