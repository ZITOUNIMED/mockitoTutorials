package com.example.mockitoTutorials.controller;

import com.example.mockitoTutorials.entity.Customer;
import com.example.mockitoTutorials.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/customers")
public class CustomerContoller {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer){
        customerService.save(customer);
    }

    @DeleteMapping
    public void deleteCustomer(Long id){
        customerService.delete(id);
    }
}

              