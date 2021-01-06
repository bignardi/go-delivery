package com.vbt.godelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbt.godelivery.dto.ProductDTO;
import com.vbt.godelivery.entities.Product;
import com.vbt.godelivery.repositories.ProductRepository;

@Service
public class ProductService {
	
	// Injeção de dependência da camada Service para Repositories
	@Autowired
	private ProductRepository repository;
	
	// Garante a transação com o Banco de Dados
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAllByOrderByNameAsc();
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList()); // Passando os dados de Product para ProductDTO usando Lambda
	}
	
}
