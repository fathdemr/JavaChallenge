package com.fatihdemir.javachallenge.controller;

import com.fatihdemir.javachallenge.dto.order.DtoOrderIU;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
public interface IOrderController {

    public ResponseEntity<DtoOrderIU> placeHolder(@PathVariable UUID customerId, @PathVariable UUID cartId);

    public ResponseEntity<DtoOrderIU> getOrderByUser(@PathVariable UUID orderCode);

    public ResponseEntity<DtoOrderIU> getAllOrdersForCustomer(@PathVariable UUID customerId);

}
