package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntityAudit {

    private String productName;

    private Double productPrice;

    private Long productQuantity;

    @OneToMany(mappedBy = "product")
    private List<CartItem> products = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

    //private Double productLastPrice; // Product'ın eski fiyatı tutulur.

}
