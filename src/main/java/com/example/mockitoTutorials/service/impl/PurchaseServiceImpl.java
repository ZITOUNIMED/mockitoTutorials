package com.example.mockitoTutorials.service.impl;

import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.repository.PurchaseRepository;
import com.example.mockitoTutorials.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements IPurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase getOne(Long id) {
        return purchaseRepository.getOne(id);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchaseRepository.delete(purchase);
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
