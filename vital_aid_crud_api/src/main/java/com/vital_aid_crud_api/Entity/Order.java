package com.vital_aid_crud_api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long oderId;
    private Integer orderedQuantity;
    private Integer totalPrice;
    private String location;

    @ManyToOne
    @JoinColumn(name = "orderMadeByUser")
    private User orderMadeByUser;

    @ManyToOne
    @JoinColumn(name = "orderMadeForProduct")
    private Product orderMadeForProduct;

    public Order() {
    }


    public Long getOderId() {
        return this.oderId;
    }

    public void setOderId(Long oderId) {
        this.oderId = oderId;
    }

    public Integer getOrderedQuantity() {
        return this.orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
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

    public User getOrderMadeByUser() {
        return this.orderMadeByUser;
    }

    public void setOrderMadeByUser(User orderMadeByUser) {
        this.orderMadeByUser = orderMadeByUser;
    }

    public Product getOrderMadeForProduct() {
        return this.orderMadeForProduct;
    }

    public void setOrderMadeForProduct(Product orderMadeForProduct) {
        this.orderMadeForProduct = orderMadeForProduct;
    }
    
}
