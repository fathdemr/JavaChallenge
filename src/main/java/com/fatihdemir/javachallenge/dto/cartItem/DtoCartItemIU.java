package com.fatihdemir.javachallenge.dto.cartItem;

import com.fatihdemir.javachallenge.dto.cart.DtoCart;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCartItemIU {

    private DtoCart cart;
    private DtoProductIU product;
    private Long quantity;
    private double productPrice;

}
