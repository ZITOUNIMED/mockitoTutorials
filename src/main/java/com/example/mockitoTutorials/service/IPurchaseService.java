package com.example.mockitoTutorials.service;

import com.example.mockitoTutorials.entity.Purchase;

import java.util.List;

public interface IPurchaseService {
    Purchase getOne(Long id);
    List<Purchase> findAll();
    void delete(Long id);
    Purchase save(Purchase purchase);
}
