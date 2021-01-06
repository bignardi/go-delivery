package com.vbt.godelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbt.godelivery.dto.OrderDTO;
import com.vbt.godelivery.dto.ProductDTO;
import com.vbt.godelivery.entities.Order;
import com.vbt.godelivery.entities.OrderStatus;
import com.vbt.godelivery.entities.Product;
import com.vbt.godelivery.repositories.OrderRepository;
import com.vbt.godelivery.repositories.ProductRepository;

@Service
public class OrderService {
	
	// Injeção de dependência da camada Service para Repositories
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	// Garante a transação com o Banco de Dados
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList()); // Passando os dados de Product para ProductDTO usando Lambda
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	// Mudando o status do pedido para entregue
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDTO(order);
	}
}
