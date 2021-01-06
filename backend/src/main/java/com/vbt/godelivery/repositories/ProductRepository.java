package com.vbt.godelivery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbt.godelivery.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	// Ordena os produtos por nome com esta função do Spring Data
	List<Product> findAllByOrderByNameAsc();
}
