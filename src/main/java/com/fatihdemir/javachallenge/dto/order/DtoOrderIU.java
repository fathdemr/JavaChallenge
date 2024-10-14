package com.fatihdemir.javachallenge.dto.order;

import com.fatihdemir.javachallenge.dto.orderItem.DtoOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrderIU {

    private List<DtoOrderItem> orderItems = new ArrayList<>();

    private LocalDate orderDate;

    private Double totalAmount;

    private String orderStatus;

}
