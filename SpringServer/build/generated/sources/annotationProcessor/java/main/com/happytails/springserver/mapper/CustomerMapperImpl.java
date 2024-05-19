package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.models.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-18T16:10:52+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer dtoToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerDTO.getId() );
        customer.firstName( customerDTO.getFirstName() );
        customer.lastName( customerDTO.getLastName() );
        customer.birthdate( customerDTO.getBirthdate() );
        customer.phone( customerDTO.getPhone() );
        customer.email( customerDTO.getEmail() );
        customer.addressWalk( customerDTO.getAddressWalk() );
        customer.photoPath( customerDTO.getPhotoPath() );

        return customer.build();
    }

    @Override
    public CustomerDTO customerToDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.id( customer.getId() );
        customerDTO.firstName( customer.getFirstName() );
        customerDTO.lastName( customer.getLastName() );
        customerDTO.birthdate( customer.getBirthdate() );
        customerDTO.phone( customer.getPhone() );
        customerDTO.email( customer.getEmail() );
        customerDTO.addressWalk( customer.getAddressWalk() );
        customerDTO.photoPath( customer.getPhotoPath() );

        return customerDTO.build();
    }
}
