package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.OrderDTO;
import com.happytails.springserver.models.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-21T02:20:35+0300",
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
        order.startDate( orderDTO.getStartDate() );
        order.endDate( orderDTO.getEndDate() );
        order.name( orderDTO.getName() );
        order.phone( orderDTO.getPhone() );
        order.address( orderDTO.getAddress() );
        order.paymentMethod( orderDTO.getPaymentMethod() );
        order.orderStatus( orderDTO.getOrderStatus() );
        order.sum( orderDTO.getSum() );
        order.clientId( orderDTO.getClientId() );
        order.petId( orderDTO.getPetId() );
        order.employeeId( orderDTO.getEmployeeId() );
        order.pet( orderDTO.getPet() );
        order.customer( orderDTO.getCustomer() );
        order.employee( orderDTO.getEmployee() );

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
        orderDTO.startDate( order.getStartDate() );
        orderDTO.endDate( order.getEndDate() );
        orderDTO.name( order.getName() );
        orderDTO.phone( order.getPhone() );
        orderDTO.address( order.getAddress() );
        orderDTO.paymentMethod( order.getPaymentMethod() );
        orderDTO.orderStatus( order.getOrderStatus() );
        orderDTO.sum( order.getSum() );
        orderDTO.clientId( order.getClientId() );
        orderDTO.petId( order.getPetId() );
        orderDTO.employeeId( order.getEmployeeId() );
        orderDTO.pet( order.getPet() );
        orderDTO.customer( order.getCustomer() );
        orderDTO.employee( order.getEmployee() );

        return orderDTO.build();
    }
}
