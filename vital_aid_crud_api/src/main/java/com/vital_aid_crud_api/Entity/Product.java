package com.vital_aid_crud_api.Entity;

import java.util.Set;

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

    private String productPhotoUrl;

    @ManyToOne
    @JoinColumn(name = "storeManagedByAdminId")
    private Admin storeManagedBy;

    @OneToMany(mappedBy = "orderMadeForProduct",cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    private Set<Order> productOrders;

    public Product() {
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

    public Set<Order> getProductOrders() {
        return this.productOrders;
    }

    public void setProductOrders(Set<Order> productOrders) {
        this.productOrders = productOrders;
    }



}
