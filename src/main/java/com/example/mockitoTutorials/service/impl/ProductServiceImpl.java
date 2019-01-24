package com.example.mockitoTutorials.service.impl;

import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.repository.ProductRepository;
import com.example.mockitoTutorials.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Product product = new Product();
        product.setId(id);
        productRepository.delete(product);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
