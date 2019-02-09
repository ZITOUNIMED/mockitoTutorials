package com.example.mockitoTutorials.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.example.mockitoTutorials.exception.InsufficientStockException;
import com.example.mockitoTutorials.exception.UnablePurchaseException;
import com.example.mockitoTutorials.entity.Customer;
import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.service.ICustomerService;
import com.example.mockitoTutorials.service.IProductService;
import com.example.mockitoTutorials.service.IPurchaseService;

@RestController()
@RequestMapping("/api/purchases")
public class PurchaseController {

	@Autowired
	private IPurchaseService purchaseService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ICustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Purchase>> getAllPurchases() {
		return ResponseEntity.ok(purchaseService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Purchase> getPurchase(@PathVariable Long id) {
		return ResponseEntity.ok(purchaseService.findById(id));
	}

	@PostMapping
	public void savePurchase(@RequestBody Purchase purchase) {
		if (purchase != null && purchase.getCustomerId() != null && purchase.getCustomerId() != null) {
			Product product = productService.findById(purchase.getProductId());
			Customer customer = customerService.findById(purchase.getCustomerId());
			if (product != null && customer != null) {
				if (product.getItems() - purchase.getItems() >= 0) {
					purchase.setId(null);
					purchaseService.save(purchase);
					product.setItems(product.getItems() - purchase.getItems());
					productService.save(product);
				} else {
					throw new InsufficientStockException(product.getItems(), purchase.getItems());
				}
			} else {
				throw new UnablePurchaseException();
			}
		}
	}

	@DeleteMapping
	public void deletePurchase(Long id) {
		purchaseService.delete(id);
	}
}
