package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.models.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer dtoToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToDto(Customer customer);

}
