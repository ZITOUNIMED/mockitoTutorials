package com.example.mockitoTutorials.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mockitoTutorials.service.IProductService;
import com.example.mockitoTutorials.service.IPurchaseService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductManagementController.class)
public class ProductManagementControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private IPurchaseService purchaseService;

	@MockBean
	private IProductService productService;
}
