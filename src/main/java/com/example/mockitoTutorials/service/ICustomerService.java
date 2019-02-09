package com.example.mockitoTutorials.service;

import com.example.mockitoTutorials.entity.Customer;

import java.util.List;

public interface ICustomerService {
    Customer findById(Long id);
    List<Customer> findAll();
    void delete(Long id);
    Customer save(Customer customer);
}
