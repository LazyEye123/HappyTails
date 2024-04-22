package com.happytails.springserver.repository;

import com.happytails.springserver.models.OrderPrices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPricesRepository extends JpaRepository<OrderPrices, Long> {
}
