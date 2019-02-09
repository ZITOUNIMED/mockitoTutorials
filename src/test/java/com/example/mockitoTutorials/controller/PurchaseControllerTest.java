package com.example.mockitoTutorials.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mockitoTutorials.entity.Customer;
import com.example.mockitoTutorials.entity.Product;
import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.exception.InsufficientStockException;
import com.example.mockitoTutorials.exception.UnablePurchaseException;
import com.example.mockitoTutorials.service.ICustomerService;
import com.example.mockitoTutorials.service.IProductService;
import com.example.mockitoTutorials.service.IPurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	private IPurchaseService purchaseService;

	@MockBean
	private IProductService productService;

	@MockBean
	private ICustomerService customerService;

	@Test
	public void should_get_purchase_by_id() throws Exception {

		Purchase purchase = new Purchase();
		purchase.setId(1L);
		purchase.setCustomerId(2L);
		purchase.setProductId(3L);
		purchase.setItems(5);

		when(purchaseService.findById(1L)).thenReturn(purchase);

		mvc.perform(get("/api/purchases/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.customerId", is(2)))
				.andExpect(jsonPath("$.productId", is(3))).andExpect(jsonPath("$.items", is(5)));
	}

	@Test
	public void get_all_purchases() throws Exception {
		when(purchaseService.findAll()).thenReturn(Arrays.asList(new Purchase(1L, 1L, 5), new Purchase(2L, 6L, 8)));

		mvc.perform(get("/api/purchases").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[1].customerId", is(2))).andExpect(jsonPath("$[1].productId", is(6)))
				.andExpect(jsonPath("$[1].items", is(8)));
	}
	
	@Test
	public void save_purchase() throws Exception {
		
		Purchase purchase = new Purchase();
		purchase.setCustomerId(2L);
		purchase.setProductId(3L);
		purchase.setItems(4);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product product = new Product();
		product.setId(3L);
		product.setUnitPrice(25.5);
		product.setItems(10);
		
		when(productService.findById(3L)).thenReturn(product);
		
		Customer customer = new Customer();
		customer.setId(2L);
		when(customerService.findById(2L)).thenReturn(customer);
		
		mvc.perform(post("/api/purchases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(purchase)))
		.andExpect(status().isOk());
		
		verify(purchaseService).save(purchase);
		product.setItems(6);
		verify(productService).save(product);
	}
	
	@Test
	public void should_throw_insufficient_exception() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setCustomerId(2L);
		purchase.setProductId(3L);
		purchase.setItems(12);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product product = new Product();
		product.setId(3L);
		product.setUnitPrice(25.5);
		product.setItems(10);
		
		when(productService.findById(3L)).thenReturn(product);
		
		Customer customer = new Customer();
		customer.setId(2L);
		when(customerService.findById(2L)).thenReturn(customer);
		
		assertThatThrownBy(() -> mvc.perform(post("/api/purchases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(purchase))))
		.hasCause(new InsufficientStockException(product.getItems(), purchase.getItems()));
		
	}
	
	@Test
	public void should_throw_unable_purchase_exception() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setCustomerId(2L);
		purchase.setProductId(3L);
		purchase.setItems(12);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Product product = new Product();
		product.setId(3L);
		product.setUnitPrice(25.5);
		product.setItems(10);
		
		when(productService.findById(3L)).thenReturn(product);
		
		when(customerService.findById(2L)).thenReturn(null);
		
		assertThatThrownBy(() -> mvc.perform(post("/api/purchases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(purchase))))
		.hasCause(new UnablePurchaseException());
		
	}
}
