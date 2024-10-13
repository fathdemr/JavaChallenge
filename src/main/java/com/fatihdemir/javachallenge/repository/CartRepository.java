package com.fatihdemir.javachallenge.repository;

import com.fatihdemir.javachallenge.entity.Cart;
import com.fatihdemir.javachallenge.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {

    @NonNull
    Optional<Cart> findById(@NonNull UUID id);

    @Query("SELECT c FROM Cart c WHERE c.customer.id = ?1 AND c.id = ?2")
    Cart findCartByCustomerIdAndCartId(UUID customerId, UUID cartId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product p WHERE p.id = ?1")
    List<Cart> findCartsByProductId(UUID productId);

}
