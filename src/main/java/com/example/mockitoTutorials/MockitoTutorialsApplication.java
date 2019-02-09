package com.example.mockitoTutorials;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.example.mockitoTutorials.entity.Customer;
import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.repository.CustomerRepository;
import com.example.mockitoTutorials.repository.ProductRepository;
import com.example.mockitoTutorials.repository.PurchaseRepository;

@SpringBootApplication
public class MockitoTutorialsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockitoTutorialsApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner initData(CustomerRepository customerRepository, ProductRepository productRepository,
			PurchaseRepository purchaseRepository) {
		return (args) -> {
			Customer customer = new Customer("Alex", "Olivier", "alexUr", "1234");
			customerRepository.save(customer);

			Product product = new Product("Chaise", 15.2, 10);
			productRepository.save(product);
			
			Purchase purchase = new Purchase(customer.getId(), product.getId(), 3);
			purchaseRepository.save(purchase);
		};
	}

}
