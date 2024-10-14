package com.fatihdemir.javachallenge.controller.impl;


import com.fatihdemir.javachallenge.controller.ICartController;
import com.fatihdemir.javachallenge.dto.cart.DtoCart;
import com.fatihdemir.javachallenge.services.impl.CartService;
import com.fatihdemir.javachallenge.util.IpAdressUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController implements ICartController {

    @Autowired
    private CartService cartService;

    @PostMapping("customer/{customerId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<DtoCart> addProductToCart(@PathVariable UUID customerId, @PathVariable UUID productId, @PathVariable Long quantity, HttpServletRequest request) {
        String clientIP = IpAdressUtil.getClientIp(request);
        DtoCart dtoCart = cartService.addProductToCart(customerId, productId, quantity, clientIP);

        return new ResponseEntity<DtoCart>(dtoCart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable UUID cartId, @PathVariable UUID productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}/cart/{cartId}")
    public ResponseEntity<DtoCart> getCart(@PathVariable UUID customerId, @PathVariable UUID cartId) {
        DtoCart dtoCart = cartService.getCart(customerId, cartId);

        return new ResponseEntity<DtoCart>(dtoCart, HttpStatus.FOUND);
    }

    @PutMapping("/{cartId}/products/{productId}/quantity/{quantity}")
    public ResponseEntity<DtoCart> updateProductQuantity(@PathVariable UUID cartId, @PathVariable UUID productId, @PathVariable Long quantity) {
        DtoCart updatedCart = cartService.updateProductQuantityInCart(cartId, productId, quantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PutMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> updateProductPrice(@PathVariable UUID cartId, @PathVariable UUID productId) {
        cartService.updateProductInCarts(cartId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable UUID cartId) {
        cartService.emptyCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
