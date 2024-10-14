package com.fatihdemir.javachallenge.repository;

import com.fatihdemir.javachallenge.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM Order o WHERE o.orderNo = ?1")
    Order findOrderByOrderCode(UUID orderCode);


    @Query("SELECT o FROM Order o WHERE o.customer.id = ?1")
    List<Order> findOrderByCustomerId(UUID customerId);

}
