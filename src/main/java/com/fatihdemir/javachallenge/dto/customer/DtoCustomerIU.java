package com.fatihdemir.javachallenge.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phoneNumber;

}
