package com.fatihdemir.javachallenge.services;

import com.fatihdemir.javachallenge.dto.cart.DtoCart;

import java.util.List;
import java.util.UUID;

public interface ICartService {

    DtoCart addProductToCart(UUID customerId, UUID productId, Long quantity, String clientIp);

    List<DtoCart> getAllCarts();

    DtoCart getCart(UUID userId, UUID cartId);

    DtoCart updateProductQuantityInCart(UUID cartId, UUID productId, Long quantity);

    void updateProductInCarts(UUID cartId, UUID productId);

    String deleteProductFromCart(UUID cartId, UUID productId);

    public void emptyCart(UUID cartId);

}
