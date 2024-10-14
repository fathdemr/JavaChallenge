package com.fatihdemir.javachallenge.dto.orderItem;

import com.fatihdemir.javachallenge.dto.order.DtoOrderIU;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderItem {

    private DtoProductIU product;

    private Long quantity;

    private double orderedProductPrice;

}
