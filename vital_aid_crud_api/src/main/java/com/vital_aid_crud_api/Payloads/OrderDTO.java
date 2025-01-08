package com.vital_aid_crud_api.Payloads;

import jakarta.validation.constraints.Positive;

public class OrderDTO {

    private Long Id;

    @Positive(message = "Quantity should be positive")
    private Integer quantity;

    @Positive(message = "Total price should be positive")
    private Integer totalPrice;

    private String location;

    private String orderMadeBy;
    private String orderMadeFor;
    private String orderedProductPhotoUrl;
    private String orderedProductCategory;

    public OrderDTO() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrderMadeBy() {
        return this.orderMadeBy;
    }

    public void setOrderMadeBy(String orderMadeBy) {
        this.orderMadeBy = orderMadeBy;
    }

    public String getOrderMadeFor() {
        return this.orderMadeFor;
    }

    public void setOrderMadeFor(String orderMadeFor) {
        this.orderMadeFor = orderMadeFor;
    }

    public String getOrderedProductPhotoUrl() {
        return this.orderedProductPhotoUrl;
    }

    public void setOrderedProductPhotoUrl(String orderedProductPhotoUrl) {
        this.orderedProductPhotoUrl = orderedProductPhotoUrl;
    }

    public String getOrderedProductCategory() {
        return this.orderedProductCategory;
    }

    public void setOrderedProductCategory(String orderedProductCategory) {
        this.orderedProductCategory = orderedProductCategory;
    }
}
