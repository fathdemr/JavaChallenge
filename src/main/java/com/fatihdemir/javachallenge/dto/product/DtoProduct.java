package com.fatihdemir.javachallenge.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoProduct {

    private String productName;

    private Double productPrice;

}
