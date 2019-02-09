package com.example.mockitoTutorials.service;

import com.example.mockitoTutorials.entity.Product;

import java.util.List;

public interface IProductService {
    Product findById(Long id);
    List<Product> findAll();
    void delete(Long id);
    Product save(Product product);
}
