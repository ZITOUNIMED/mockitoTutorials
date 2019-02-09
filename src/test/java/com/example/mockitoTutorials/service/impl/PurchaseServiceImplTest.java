package com.example.mockitoTutorials.service.impl;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.mockitoTutorials.entity.Purchase;
import com.example.mockitoTutorials.repository.PurchaseRepository;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {
	
	@Mock
	private PurchaseRepository purchaseRepository;
	
	@InjectMocks
	private PurchaseServiceImpl purchaseServiceImpl = new PurchaseServiceImpl();
	
	@Test
	public void should_save_purchase() {
		Purchase mockPurchase = new Purchase();
		mockPurchase.setId(1L);
		when(purchaseRepository.save(anyObject())).thenReturn(mockPurchase);
		
		Purchase purchase = new Purchase();
		Purchase result = purchaseServiceImpl.save(purchase);
		assertNotNull(result);
		assertNotNull(result.getId());
	}

}
