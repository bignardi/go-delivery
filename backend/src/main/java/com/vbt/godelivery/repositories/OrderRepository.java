package com.vbt.godelivery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vbt.godelivery.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	// JPQL do JPA
	@Query("SELECT DISTINCT obj "
			+ "FROM Order obj "
			+ "JOIN FETCH obj.products "
			+ "WHERE obj.status = 0 "
			+ "ORDER BY obj.moment ASC "
			)
	List<Order> findOrderWithProducts();
}
