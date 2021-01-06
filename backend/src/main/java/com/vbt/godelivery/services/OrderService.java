package com.vbt.godelivery.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbt.godelivery.dto.OrderDTO;
import com.vbt.godelivery.entities.Order;
import com.vbt.godelivery.repositories.OrderRepository;

@Service
public class OrderService {
	
	// Injeção de dependência da camada Service para Repositories
	@Autowired
	private OrderRepository repository;
	
	// Garante a transação com o Banco de Dados
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList()); // Passando os dados de Product para ProductDTO usando Lambda
	}
	
}
