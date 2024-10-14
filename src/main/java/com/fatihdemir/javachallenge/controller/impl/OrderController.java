package com.fatihdemir.javachallenge.controller.impl;

import com.fatihdemir.javachallenge.controller.IOrderController;
import com.fatihdemir.javachallenge.dto.order.DtoOrderIU;
import com.fatihdemir.javachallenge.services.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController implements IOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/customer/{customerId}/carts/{cartId}")
    public ResponseEntity<DtoOrderIU> placeHolder(@PathVariable UUID customerId,@PathVariable UUID cartId){
        DtoOrderIU dtoOrderIU = orderService.placeOrder(customerId, cartId);

        return new ResponseEntity<DtoOrderIU>(dtoOrderIU, HttpStatus.CREATED);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<DtoOrderIU> getOrderByUser(@PathVariable UUID orderCode) {
        DtoOrderIU dtoOrderIU = orderService.getOrderForCode(orderCode);

        return new ResponseEntity<DtoOrderIU>(dtoOrderIU, HttpStatus.FOUND);
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<DtoOrderIU> getAllOrdersForCustomer(@PathVariable UUID customerId) {
        DtoOrderIU dtoOrderIU = orderService.getAllOrdersForCustomer(customerId);

        return new ResponseEntity<DtoOrderIU>(dtoOrderIU, HttpStatus.FOUND);
    }

}
