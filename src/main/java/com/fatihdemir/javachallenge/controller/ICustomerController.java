package com.fatihdemir.javachallenge.controller;

import com.fatihdemir.javachallenge.dto.customer.DtoCustomer;
import com.fatihdemir.javachallenge.dto.customer.DtoCustomerIU;
import jakarta.servlet.http.HttpServletRequest;

public interface ICustomerController {

    public DtoCustomer addCustomer(DtoCustomerIU dtoCustomerIU, HttpServletRequest request);

}
