package com.fatihdemir.javachallenge.services.impl;

import com.fatihdemir.javachallenge.dto.order.DtoOrder;
import com.fatihdemir.javachallenge.dto.order.DtoOrderIU;
import com.fatihdemir.javachallenge.dto.orderItem.DtoOrderItem;
import com.fatihdemir.javachallenge.entity.*;
import com.fatihdemir.javachallenge.exceptions.ResourceNotFoundException;
import com.fatihdemir.javachallenge.exceptions.ShowException;
import com.fatihdemir.javachallenge.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public OrderItemRepository orderItemRepository;

    @Autowired
    public CartItemRepository cartItemRepository;

    @Autowired
    public CustomerService customerService;

    @Autowired
    public CartService cartService;

    @Autowired
    public ModelMapper modelMapper;

    public DtoOrderIU placeOrder(UUID customerId, UUID cartId){

        Cart cart = cartRepository.findCartByCustomerIdAndCartId(customerId, cartId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId));

        Order order = new Order();

        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Accepted Order");
        order.setOrderNo(order.generateOrderNo());
        order.setCustomer(customer);


        Order savedOrder = orderRepository.save(order);

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            throw new ShowException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(savedOrder);

            orderItems.add(orderItem);
        }

        orderItems = orderItemRepository.saveAll(orderItems);

        cart.getCartItems().forEach(item -> {
            Long quantity = item.getQuantity();

            Product product = item.getProduct();

            cartService.deleteProductFromCart(cartId, item.getProduct().getId());

            product.setProductQuantity(product.getProductQuantity() - quantity);
        });

        DtoOrderIU dtoOrderIU = modelMapper.map(savedOrder, DtoOrderIU.class);

        orderItems.forEach(item -> dtoOrderIU.getOrderItems().add(modelMapper.map(item, DtoOrderItem.class)));

        return null;
    }

    public DtoOrderIU getOrderForCode(UUID orderCode) {

        Order order = orderRepository.findOrderByOrderCode(orderCode);

        if (order == null) {
            throw new ResourceNotFoundException("Order", "orderId", orderCode);
        }

        return modelMapper.map(order, DtoOrderIU.class);
    }

    public DtoOrderIU getAllOrdersForCustomer(UUID customerId) {

       List<Order> orders = orderRepository.findOrderByCustomerId(customerId);

       if (orders == null) {
           throw new ResourceNotFoundException("Customer", "customerId", customerId);
       }

       DtoOrderIU dtoOrderIU = new DtoOrderIU();

       for (Order order : orders) {
           dtoOrderIU = modelMapper.map(order, DtoOrderIU.class);
       }

       return dtoOrderIU;
    }



}
















