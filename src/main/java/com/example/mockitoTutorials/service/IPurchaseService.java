package com.example.mockitoTutorials.service;

import com.example.mockitoTutorials.entity.Purchase;

import java.util.List;

public interface IPurchaseService {
    Purchase findById(Long id);
    List<Purchase> findAll();
    void delete(Long id);
    Purchase save(Purchase purchase);
    List<Purchase> findByCustomerId(Long customerId);
    List<Purchase> findByProductId(Long productId);
}
