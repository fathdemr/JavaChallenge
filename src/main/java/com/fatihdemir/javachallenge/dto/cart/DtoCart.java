package com.fatihdemir.javachallenge.dto.cart;

import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCart {

    private Double totalPrice = 0.0;
    private List<DtoProductIU> products = new ArrayList<>();

}
