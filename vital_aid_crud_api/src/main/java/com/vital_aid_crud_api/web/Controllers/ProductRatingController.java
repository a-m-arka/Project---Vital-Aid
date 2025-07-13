package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.ProductDTO;
import com.vital_aid_crud_api.Payloads.ProductRatingDTO;
import com.vital_aid_crud_api.service.Interfaces.ProductRatingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/productRating")
public class ProductRatingController {

    @Autowired
    private ProductRatingService productRatingService;

// ====================================================================================
//                                        LIST OF ALL RATINGS
    @GetMapping("/listOfAllRatings")
    public ResponseEntity<List<ProductRatingDTO>> getListOfAllRatings(){
        List<ProductRatingDTO> ratings = productRatingService.getAllProductRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
// ====================================================================================


// ====================================================================================
//                                 LIST OF ALL RATINGS MADE BY USER(EMAIL)
    @GetMapping("/ratingsMadeByUser")
    public ResponseEntity<List<ProductRatingDTO>> getRatingsMadeByAnUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ProductRatingDTO> userRatings = productRatingService.getAllRatingsMadeByUser(userEmail);
        return new ResponseEntity<>(userRatings, HttpStatus.OK);
    }
// ====================================================================================


// ====================================================================================
//                                        LIST OF ALL RATINGS MADE BY USER(ID)
    @GetMapping("/ratingsMadeByUserId/{Id}")
    public ResponseEntity<List<ProductRatingDTO>> getRatingsMadeByAnUserId(@PathVariable Long Id) {
        List<ProductRatingDTO> userRatings = productRatingService.getAllRatingsMadeByUserId(Id);
        return new ResponseEntity<>(userRatings, HttpStatus.OK);
    }
// ====================================================================================



// ====================================================================================
//                                       LIST OF ALL RATINGS MADE FOR A PRODUCT
    @GetMapping("/ratingsForProduct/{Id}")
    public ResponseEntity<List<ProductRatingDTO>> getRatingsForProduct(@PathVariable Long Id) {
        List<ProductRatingDTO> productRatings = productRatingService.getProductRatingListByProductId(Id);
        return new ResponseEntity<>(productRatings, HttpStatus.OK);
    }
// ====================================================================================



// ====================================================================================
//                                        RATING A PRODUCT
    @PostMapping("/rateProduct/{Id}")
    public ResponseEntity<ProductRatingDTO> rateAProduct(@Valid @RequestBody ProductRatingDTO productRatingDTO,@PathVariable Long Id){
        ProductRatingDTO ratedProduct = productRatingService.rateProduct(Id, productRatingDTO);
        return new ResponseEntity<>(ratedProduct, HttpStatus.CREATED);
    }
// ====================================================================================


// ====================================================================================
//                                        UPDATING A PRODUCT RATING
    @PutMapping("/updateProductRating/{Id}")
    public ResponseEntity<ProductRatingDTO> updateRating(@PathVariable Long Id, @Valid @RequestBody ProductRatingDTO productRatingDTO) {
        ProductRatingDTO updatedRating = productRatingService.updateProductRating(Id, productRatingDTO);
        return new ResponseEntity<>(updatedRating, HttpStatus.OK);
    }

// ====================================================================================


// ====================================================================================
//                                        DELETING A PRODUCT RATING
    @DeleteMapping("/deleteProductRating/{Id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long Id) {
        productRatingService.deleteProductRating(Id);
        return new ResponseEntity<>("Product rating deleted successfully", HttpStatus.OK);
    }
// ====================================================================================


// ====================================================================================
//                                        GET PRODUCT RATING BY ID
    @GetMapping("/getProductRatingById/{Id}")
    public ResponseEntity<ProductDTO> getProductRatingById(@PathVariable Long Id) {
        ProductDTO ratedProduct = productRatingService.getProductRatingById(Id);
        return new ResponseEntity<>(ratedProduct, HttpStatus.OK);
    }
// ====================================================================================
}
