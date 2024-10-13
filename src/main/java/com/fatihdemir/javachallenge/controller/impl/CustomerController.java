package com.fatihdemir.javachallenge.controller.impl;

import com.fatihdemir.javachallenge.util.IpAdressUtil;
import com.fatihdemir.javachallenge.controller.ICustomerController;
import com.fatihdemir.javachallenge.dto.customer.DtoCustomer;
import com.fatihdemir.javachallenge.dto.customer.DtoCustomerIU;
import com.fatihdemir.javachallenge.services.impl.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController implements ICustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/register")
    @Override
    public DtoCustomer addCustomer(@RequestBody DtoCustomerIU dtoCustomerIU, HttpServletRequest request) {
        String clientIP = IpAdressUtil.getClientIp(request);
        return customerService.addCustomer(dtoCustomerIU, clientIP);
    }



}
