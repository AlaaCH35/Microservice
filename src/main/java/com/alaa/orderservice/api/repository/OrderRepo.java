package com.alaa.orderservice.api.repository;

import com.alaa.orderservice.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo  extends JpaRepository<Order,Integer> {
}
