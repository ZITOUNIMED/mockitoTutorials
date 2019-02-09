package com.example.mockitoTutorials.controller;

import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/products")
public class ProductContoller {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public void saveProduct(@RequestBody Product product){
        productService.save(product);
    }

    @DeleteMapping
    public void deleteProduct(Long id){
        productService.delete(id);
    }
}
