package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.OrderDTO;
import com.happytails.springserver.models.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToOrder(OrderDTO orderDTO);
    OrderDTO orderToDto(Order order);
}
