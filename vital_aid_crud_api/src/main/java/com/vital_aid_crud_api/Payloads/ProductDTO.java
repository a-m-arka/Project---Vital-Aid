package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name can not be empty")
    private String productName;

    @NotBlank(message = "Product category can not be empty")
    private String productCategory;

    @Min(value = 0, message = "Product price can not be negative")
    private Integer productPrice;

    @Min(value = 0, message = "Product stock quantity can not be negative")
    private Integer productStockQuantity;

    private String productPhotoUrl;

    private Float productAverageRating;

    public ProductDTO() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPhotoUrl() {
        return this.productPhotoUrl;
    }

    public void setProductPhotoUrl(String productPhotoUrl) {
        this.productPhotoUrl = productPhotoUrl;
    }

    public Integer getProductStockQuantity() {
        return this.productStockQuantity;
    }

    public void setProductStockQuantity(Integer productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public Float getProductAverageRating() {
        return this.productAverageRating;
    }

    public void setProductAverageRating(Float productAverageRating) {
        this.productAverageRating = productAverageRating;
    }
    

    
}
