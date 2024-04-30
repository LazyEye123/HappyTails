package com.happytails.springserver.repository;

import com.happytails.springserver.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
