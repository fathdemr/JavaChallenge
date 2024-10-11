package com.fatihdemir.javachallenge.services;

import com.fatihdemir.javachallenge.dto.customer.DtoCustomer;
import com.fatihdemir.javachallenge.dto.customer.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer addCustomer(DtoCustomerIU dtoCustomerIU, String clientIP);


}
