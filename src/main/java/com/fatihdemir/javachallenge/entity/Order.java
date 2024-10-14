package com.fatihdemir.javachallenge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntityAudit{

    private LocalDate orderDate;

    private UUID orderNo;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Customer customer;

    private Double totalAmount;

    private String orderStatus;

    public UUID generateOrderNo() {
        return UUID.randomUUID();
    }

}
