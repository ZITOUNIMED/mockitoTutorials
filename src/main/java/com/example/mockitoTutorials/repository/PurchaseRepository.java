package com.example.mockitoTutorials.repository;

import com.example.mockitoTutorials.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.Long;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	List<Purchase> findByCustomerId(Long customerId);
	List<Purchase> findByProductId(Long productId);
}
