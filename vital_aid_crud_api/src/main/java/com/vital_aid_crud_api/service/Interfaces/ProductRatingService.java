package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.ProductDTO;
import com.vital_aid_crud_api.Payloads.ProductRatingDTO;

public interface ProductRatingService {

    ProductRatingDTO rateProduct(Long productId, ProductRatingDTO productRatingDTO);
    void deleteProductRating(Long productRatingId);
    ProductRatingDTO updateProductRating(Long productRatingId, ProductRatingDTO productRatingDTO);
    List<ProductRatingDTO>getProductRatingListByProductId(Long productId);
    ProductDTO getProductRatingById(Long productId);

    List<ProductRatingDTO> getAllProductRatings();
    List<ProductRatingDTO> getAllRatingsMadeByUser(String userEmail);
    List<ProductRatingDTO> getAllRatingsMadeByUserId(Long userId);
}
