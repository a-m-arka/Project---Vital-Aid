package com.vital_aid_crud_api.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_store_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;


    private String productName;

    private String productCategory;

    private Integer productPrice;

    private Integer productStockQuantity;

    private String productPhotoUrl;

    private Float productAverageRating;


    @ManyToOne
    @JoinColumn(name = "storeManagedByAdminId")
    private Admin storeManagedBy;

    @OneToMany(mappedBy = "orderMadeForProduct",cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = false)
    private List<Order> productOrders;

    @OneToMany(mappedBy = "pRatingMadeForProduct", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<ProductRating> productRatings;

    public Product() {
        this.productAverageRating = 0.0f;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Admin getStoreManagedBy() {
        return this.storeManagedBy;
    }

    public void setStoreManagedBy(Admin storeManagedBy) {
        this.storeManagedBy = storeManagedBy;
    }

    public List<Order> getProductOrders() {
        return this.productOrders;
    }

    public void setProductOrders(List<Order> productOrders) {
        this.productOrders = productOrders;
    }

    public Integer getProductStockQuantity() {
        return this.productStockQuantity;
    }

    public void setProductStockQuantity(Integer productStockQuantity) {
        this.productStockQuantity = productStockQuantity;
    }

    public List<ProductRating> getProductRatings() {
        return this.productRatings;
    }

    public void setProductRatings(List<ProductRating> productRatings) {
        this.productRatings = productRatings;
    }

    public Float getProductAverageRating() {
        return this.productAverageRating;
    }

    public void setProductAverageRating(Float productAverageRating) {
        this.productAverageRating = productAverageRating;
    }



}
