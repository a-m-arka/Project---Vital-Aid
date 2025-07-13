package com.vital_aid_crud_api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_ratings_reviews")
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productRatingId;
    private Float rating;
    private String review;

    @ManyToOne
    @JoinColumn(name = "productRatingMadeByUser")
    private User productRatingMadeByUser;

    @ManyToOne
    @JoinColumn(name = "pRatingMadeForProduct")
    private Product pRatingMadeForProduct;

    public ProductRating() {
    }

    public Long getProductRatingId() {
        return productRatingId;
    }

    public void setProductRatingId(Long productRatingId) {
        this.productRatingId = productRatingId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getProductRatingMadeByUser() {
        return productRatingMadeByUser;
    }

    public void setProductRatingMadeByUser(User productRatingMadeByUser) {
        this.productRatingMadeByUser = productRatingMadeByUser;
    }

    public Product getpRatingMadeForProduct() {
        return pRatingMadeForProduct;
    }

    public void setpRatingMadeForProduct(Product pRatingMadeForProduct) {
        this.pRatingMadeForProduct = pRatingMadeForProduct;
    }

}
