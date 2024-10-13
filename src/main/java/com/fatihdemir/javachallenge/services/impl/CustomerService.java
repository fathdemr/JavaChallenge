package com.fatihdemir.javachallenge.services.impl;

import com.fatihdemir.javachallenge.dto.customer.DtoCustomer;
import com.fatihdemir.javachallenge.dto.customer.DtoCustomerIU;
import com.fatihdemir.javachallenge.entity.Customer;
import com.fatihdemir.javachallenge.repository.CustomerRepository;
import com.fatihdemir.javachallenge.services.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatihdemir.javachallenge.security.Hash;

import java.security.NoSuchAlgorithmException;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DtoCustomer addCustomer(DtoCustomerIU dtoCustomerIU, String clientIP) {

        DtoCustomer response = new DtoCustomer();
        Customer customer = new Customer();
        Hash hash = new Hash();

        BeanUtils.copyProperties(dtoCustomerIU, customer);

        try {
            customer.setPassword(hash.HashMD5(dtoCustomerIU.getPassword()));
        }catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());;
        }

        customer.setCreatedBy(clientIP);

        Customer dbStudent = customerRepository.save(customer);


        BeanUtils.copyProperties(dbStudent, response);
        return response;

    }


}
