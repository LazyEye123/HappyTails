package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.OrderDTO;
import com.happytails.springserver.models.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-19T17:34:09+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order dtoToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderDTO.getId() );
        order.serviceType( orderDTO.getServiceType() );
        order.date( orderDTO.getDate() );
        order.paymentMethod( orderDTO.getPaymentMethod() );
        order.orderStatus( orderDTO.getOrderStatus() );
        order.sum( orderDTO.getSum() );
        order.clientId( orderDTO.getClientId() );
        order.petId( orderDTO.getPetId() );
        order.employeeId( orderDTO.getEmployeeId() );

        return order.build();
    }

    @Override
    public OrderDTO orderToDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO.OrderDTOBuilder orderDTO = OrderDTO.builder();

        orderDTO.id( order.getId() );
        orderDTO.serviceType( order.getServiceType() );
        orderDTO.date( order.getDate() );
        orderDTO.paymentMethod( order.getPaymentMethod() );
        orderDTO.orderStatus( order.getOrderStatus() );
        orderDTO.sum( order.getSum() );
        orderDTO.clientId( order.getClientId() );
        orderDTO.petId( order.getPetId() );
        orderDTO.employeeId( order.getEmployeeId() );

        return orderDTO.build();
    }
}
