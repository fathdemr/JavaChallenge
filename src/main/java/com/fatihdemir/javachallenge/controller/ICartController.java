package com.fatihdemir.javachallenge.controller;

import com.fatihdemir.javachallenge.dto.cart.DtoCart;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface ICartController {

    public ResponseEntity<DtoCart> addProductToCart(@PathVariable UUID userId, @PathVariable UUID productId, @PathVariable Long quantity, HttpServletRequest request);

    public ResponseEntity<String> deleteProductFromCart(@PathVariable UUID cartId, @PathVariable UUID productId);

    public ResponseEntity<DtoCart> getCart(@PathVariable UUID customerId, @PathVariable UUID cartId);

    public ResponseEntity<DtoCart> updateProductQuantity(@PathVariable UUID cartId, @PathVariable UUID productId, @PathVariable Long quantity);

    public ResponseEntity<Void> updateProductPrice(@PathVariable UUID cartId, @PathVariable UUID productId);

}
