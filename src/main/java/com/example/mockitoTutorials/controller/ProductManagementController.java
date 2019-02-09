package com.example.mockitoTutorials.controller;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mockitoTutorials.controller.request.AddProductItemsRequest;
import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.service.IProductService;
import com.example.mockitoTutorials.service.IPurchaseService;

@RestController
@RequestMapping("/api/product-management")
public class ProductManagementController {

	@Autowired
	private IPurchaseService purchaseService;

	@Autowired
	private IProductService productService;

	// add items to product
	@PostMapping("/add-product-items")
	public void addProductItems(@RequestBody AddProductItemsRequest request) {
		if (request != null) {
			Product product = productService.findById(request.getProductId());
			if (product != null) {
				product.setItems(product.getItems() + request.getItems());
				productService.save(product);
			}
		}
	}

	// fetch all customer bought products
	@GetMapping("/customer-products/{customerId}")
	public ResponseEntity<List<Product>> fetchCustomerProducts(@PathVariable Long customerId) {
		List<Purchase> purchases = purchaseService.findByCustomerId(customerId);
		return ResponseEntity.ok(
				purchases.stream().map(purchase -> productService.findById(purchase.getProductId())).collect(toList()));
	}

	// display total product sales
	@GetMapping("/display-total-product-sales/{productId}")
	public ResponseEntity<Double> displayTotalProductSales(@PathVariable Long productId) {
		List<Purchase> purchases = purchaseService.findByProductId(productId);
		Product product = productService.findById(productId);
		double total = purchases.stream().mapToDouble(purchase -> purchase.getItems() * product.getUnitPrice()).sum();
		return ResponseEntity.ok(total);
	}
}
