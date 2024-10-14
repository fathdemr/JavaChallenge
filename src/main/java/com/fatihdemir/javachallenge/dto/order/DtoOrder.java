package com.fatihdemir.javachallenge.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrder {

    private String orderedProductName;

    private Long orderedQuantity;

    private Long orderedProductPrice;

    private LocalDate orderedDate;

    private String orderedStatus;

}
