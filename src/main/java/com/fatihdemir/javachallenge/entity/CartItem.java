package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntityAudit {

    @ManyToOne
    @JoinColumn
    private Cart cart;

    @ManyToOne
    @JoinColumn
    private Product product;

    private Long quantity;
    private double productPrice;

}
