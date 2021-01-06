package com.vbt.godelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbt.godelivery.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
