package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Entity.Product;
import com.vital_aid_crud_api.Entity.ProductRating;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.ProductDTO;
import com.vital_aid_crud_api.Payloads.ProductRatingDTO;
import com.vital_aid_crud_api.repository.ProductRatingRepository;
import com.vital_aid_crud_api.repository.ProductRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.ProductRatingService;

import jakarta.transaction.Transactional;

@Service
public class ProductRatingServiceImpl implements ProductRatingService{

    @Autowired
    private ProductRatingRepository productRatingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;


// ====================================================================================================
                                    // LIST OF ALL PRODUCT RATINGS
    @Override
    @Transactional
    public List<ProductRatingDTO> getAllProductRatings(){
        List<ProductRatingDTO> productRatings = productRatingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
        
        return productRatings;
    }
// ====================================================================================================



// ====================================================================================================
                                // LIST OF ALL PRODUCT RATINGS MADE BY A USER(EMAIL)
    @Override
    @Transactional
    public List<ProductRatingDTO> getAllRatingsMadeByUser(String userEmail){
        User user = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        List<ProductRating> productRatings = user.getProductsRated();
        List<ProductRatingDTO> productRatingDTOs = productRatings.stream()
                .map(this::convertToDTO)
                .toList();
        return productRatingDTOs;
    }
// ====================================================================================================

// ====================================================================================================
                                    // LIST OF ALL PRODUCT RATINGS MADE BY A USER(ID)
    @Override
    @Transactional
    public List<ProductRatingDTO> getAllRatingsMadeByUserId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<ProductRating> productRatings = user.getProductsRated();
        List<ProductRatingDTO> productRatingDTOs = productRatings.stream()
                .map(this::convertToDTO)
                .toList();
        return productRatingDTOs;
    }
// ====================================================================================================

// ====================================================================================================
                                        // RATE A PRODUCT
    @Override
    @Transactional
    public ProductRatingDTO rateProduct(Long productId, ProductRatingDTO productRatingDTO){
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByPersonEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        Product ratedProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        ProductRating productRating = convertToEntity(productRatingDTO);
        productRating.setProductRatingMadeByUser(user);
        productRating.setpRatingMadeForProduct(ratedProduct);

        List<ProductRating> productRatings = ratedProduct.getProductRatings();
        if(productRatings == null || productRatings.isEmpty()){
            ratedProduct.setProductAverageRating(0f);
        }else{
            float averageRating = productRatings.stream()
                    .map(ProductRating::getRating)
                    .reduce(0f, Float::sum) / productRatings.size();
            ratedProduct.setProductAverageRating(averageRating);
        }

        productRating = productRatingRepository.save(productRating);
        return convertToDTO(productRating);
    }
// ====================================================================================================

// ====================================================================================================
                                    // DELETE A PRODUCT RATING
    @Override
    @Transactional
    public void deleteProductRating(Long productRatingId){
        ProductRating productRating = productRatingRepository.findById(productRatingId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Rating", "id", productRatingId));
        User ratedUser = productRating.getProductRatingMadeByUser();
        Product ratedProduct = productRating.getpRatingMadeForProduct();

        ratedUser.getProductsRated().remove(productRating);
        userRepository.save(ratedUser);

        ratedProduct.getProductRatings().remove(productRating);

        

        List<ProductRating> productRatings = ratedProduct.getProductRatings();
        
        if(productRatings != null && !productRatings.isEmpty()){
            if(!productRatings.isEmpty()){
                float averageRating = productRatings.stream()
                        .map(ProductRating::getRating)
                        .reduce(0f, Float::sum) / productRatings.size();
                ratedProduct.setProductAverageRating(averageRating);
            }else{
                ratedProduct.setProductAverageRating(0f);
            }
        }else{
            ratedProduct.setProductAverageRating(0f);
        }
        productRepository.save(ratedProduct);

        productRatingRepository.delete(productRating);
    }
// ====================================================================================================

// ====================================================================================================
                                    // UPDATE A PRODUCT RATING
    @Override
    @Transactional
    public ProductRatingDTO updateProductRating(Long productRatingId, ProductRatingDTO productRatingDTO){
        ProductRating existingProductRating = productRatingRepository.findById(productRatingId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Rating", "id", productRatingId));
        Product ratedProduct = existingProductRating.getpRatingMadeForProduct();

        existingProductRating.setRating(productRatingDTO.getRating());
        existingProductRating.setReview(productRatingDTO.getReview());
        existingProductRating = productRatingRepository.save(existingProductRating);
        List<ProductRating> productRatings = ratedProduct.getProductRatings();
        if(productRatings == null || productRatings.isEmpty()){
            ratedProduct.setProductAverageRating(productRatingDTO.getRating());
        }else{
            float averageRating = productRatings.stream()
                    .map(ProductRating::getRating)
                    .reduce(0f, Float::sum) / productRatings.size();
            ratedProduct.setProductAverageRating(averageRating);
        }
        productRepository.save(ratedProduct);
        return convertToDTO(existingProductRating);
    }
// ====================================================================================================

// ====================================================================================================
                                    // PRODUCT RATINGS LIST BY PRODUCT ID
    @Override
    @Transactional
    public List<ProductRatingDTO> getProductRatingListByProductId(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        List<ProductRating> productRatings = product.getProductRatings();
        if(productRatings == null || productRatings.isEmpty()){
            return List.of();
        }else{
            return productRatings.stream()
                    .map(this::convertToDTO)
                    .toList();
        }
    }
// ====================================================================================================

// ====================================================================================================
                                    // PRODUCT RATING BY ID
    @Override
    @Transactional
    public ProductDTO getProductRatingById(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProductAverageRating(product.getProductAverageRating());
        return productDTO;
    }

// ====================================================================================================


// ====================================================================================================
                                    // HELPER METHODS

    private ProductRating convertToEntity(ProductRatingDTO productRatingDTO){
        return modelMapper.map(productRatingDTO, ProductRating.class);
    }
                            
    private ProductRatingDTO convertToDTO(ProductRating productRating) {
        ProductRatingDTO productRatingDTO = modelMapper.map(productRating, ProductRatingDTO.class);
        productRatingDTO.setProductRatedByUserName(productRating.getProductRatingMadeByUser().getPersonName());
        productRatingDTO.setpRatingForProductName(productRating.getpRatingMadeForProduct().getProductName());
        return productRatingDTO;
    }

}
