package com.example.mockitoTutorials.service.impl;

import com.example.mockitoTutorials.entity.Customer;
import com.example.mockitoTutorials.repository.CustomerRepository;
import com.example.mockitoTutorials.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customerRepository.delete(customer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
