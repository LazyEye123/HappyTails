package com.happytails.springserver.service;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.OrderDTO;
import com.happytails.springserver.dto.PetDTO;
import com.happytails.springserver.mapper.CustomerMapperImpl;
import com.happytails.springserver.mapper.OrderMapperImpl;
import com.happytails.springserver.mapper.PetMapperImpl;
import com.happytails.springserver.models.*;
import com.happytails.springserver.repository.CustomerRepository;
import com.happytails.springserver.repository.OrderRepository;
import com.happytails.springserver.repository.PetRepository;
import com.happytails.springserver.repository.UsersRepository;
import com.happytails.springserver.validation.UserAlreadyExsistException;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UsersRepository usersRepository;
    private final CustomerMapperImpl customerMapper;
    private final OrderMapperImpl orderMapper;
    private final OrderRepository orderRepository;
    private final PetMapperImpl petMapper;
    private final PetRepository petRepository;
    private DatabaseReader databaseReader;

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

    public boolean checkIsUserNotExists(String username) throws UserAlreadyExsistException {
        if (usersRepository.findByUsername(username)!=null) {
            throw new UserAlreadyExsistException(String.format("Пользователь с логином %s уже существует.", username));
        }
        return true;
    }

    public CityResponse getUserLocation(String ipAddress) throws IOException, GeoIp2Exception {
        databaseReader = new DatabaseReader
                .Builder(new File("./src/main/resources/geolite/GeoLite2-City.mmdb"))
                .build();
        return databaseReader.city(InetAddress.getByName(ipAddress)); //109.106.143.87
    }

    public Pet savePet(PetDTO petDTO) {
        return petRepository.save(petMapper.dtoToPet(petDTO));
    }

    public void deletePet(PetDTO petDTO) {
        petRepository.delete(petMapper.dtoToPet(petDTO));
    }

    public Order createOrder(OrderDTO orderDTO) {
        return orderRepository.save(orderMapper.dtoToOrder(orderDTO));
    }
}
