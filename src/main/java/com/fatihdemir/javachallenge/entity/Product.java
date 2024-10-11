package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntityAudit {

    private String productName;

    private Double productPrice;

    private Long productQuantity;

    //private Double productLastPrice; // Product'ın eski fiyatı tutulur.

}
