package com.fatihdemir.javachallenge.services;

import com.fatihdemir.javachallenge.dto.order.DtoOrderIU;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderService {

    public DtoOrderIU placeOrder(UUID customerId, UUID cartId);

    public DtoOrderIU getOrderForCode(UUID orderCode);

    public DtoOrderIU getAllOrdersForCustomer(UUID customerId);

}
