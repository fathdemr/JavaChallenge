package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntityAudit{

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    private Long quantity;

    private double orderedProductPrice; // this keeps the price was brought current.

}
