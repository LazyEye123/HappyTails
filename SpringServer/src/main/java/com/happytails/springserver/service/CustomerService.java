package com.happytails.springserver.service;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.mapper.CustomerMapperImpl;
import com.happytails.springserver.models.*;
import com.happytails.springserver.repository.CustomerRepository;
import com.happytails.springserver.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UsersRepository usersRepository;
    private final CustomerMapperImpl customerMapper;
    public void save(CustomerDTO customerDTO) {
        var customer = customerMapper.dtoToCustomer(customerDTO);
        var u = usersRepository.save(Users
                .builder()
                .username(customerDTO.getLogin())
                .password(new BCryptPasswordEncoder().encode(customerDTO.getPassword()))
                .roles("CLIENT")
                .build());
        customer.setUsersId(u.getId());
        customerRepository.save(customer);
    }
}
