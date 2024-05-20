package com.happytails.springserver.service;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.EmployeeDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

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

    public CustomerDTO save(CustomerDTO customerDTO) {
        var customer = customerMapper.dtoToCustomer(customerDTO);
        var u = usersRepository.save(Users
                .builder()
                .username(customerDTO.getLogin())
                .password(new BCryptPasswordEncoder().encode(customerDTO.getPassword()))
                .roles("CLIENT")
                .build());
        customer.setUsersId(u.getId());
        return customerMapper.customerToDto(customerRepository.save(customer));
    }

    public boolean checkIsUserNotExists(String username) throws UserAlreadyExsistException {
        if (usersRepository.findByUsername(username) != null) {
            throw new UserAlreadyExsistException(String.format("Пользователь с логином %s уже существует.", username));
        }
        return true;
    }

    public CustomerDTO getUserByUsername(String username) {
        var customer = customerRepository.findByUsersId(usersRepository.findByUsername(username).getId());
        return customerMapper.customerToDto(customer);
    }

    public CityResponse getUserLocation(String ipAddress) throws IOException, GeoIp2Exception {
        databaseReader = new DatabaseReader
                .Builder(new File("./src/main/resources/geolite/GeoLite2-City.mmdb"))
                .build();
        return databaseReader.city(InetAddress.getByName(ipAddress)); //109.106.143.87
    }

    public PetDTO uploadPetPhoto(MultipartFile photo, Long petId) throws IOException {
        var pet = petRepository.findById(petId).get();
        if (!photo.isEmpty()) {
            StringBuilder saveLocation =  new StringBuilder(System.getProperty("user.dir"));
            saveLocation.append("/src/main/resources/uploaded/images/pets");
            var file = new File(saveLocation.toString());
            if (!file.exists()) {
                file.mkdir();
            }
            saveLocation.append("/");
            saveLocation.append(petId);
            saveLocation.append(".");
            saveLocation.append(photo.getOriginalFilename().split("[.]")[1]);
            try {
                photo.transferTo(new File(saveLocation.toString()));
            } catch (IOException e) {
                throw new IOException(e);
            }
            pet.setPhotoPath(saveLocation.toString());
            return petMapper.petToDto(petRepository.save(pet));
        }
        return petMapper.petToDto(pet);
    }

    public CustomerDTO uploadCustomerPhoto(MultipartFile photo, Long customerId) throws IOException {
        var customer = customerRepository.findById(customerId).get();
        if (!photo.isEmpty()) {
            StringBuilder saveLocation =  new StringBuilder(System.getProperty("user.dir"));
            saveLocation.append("/src/main/resources/uploaded/images/customers");
            var file = new File(saveLocation.toString());
            if (!file.exists()) {
                file.mkdir();
            }
            saveLocation.append("/");
            saveLocation.append(customerId);
            saveLocation.append(".");
            saveLocation.append(photo.getOriginalFilename().split("[.]")[1]);
            try {
                photo.transferTo(new File(saveLocation.toString()));
            } catch (IOException e) {
                throw new IOException(e);
            }
            customer.setPhotoPath(saveLocation.toString());
            return customerMapper.customerToDto(customerRepository.save(customer));
        }
        return customerMapper.customerToDto(customer);
    }

    public PetDTO savePet(PetDTO petDTO) {
        var pet = petMapper.dtoToPet(petDTO);
        return petMapper.petToDto(petRepository.save(pet));
    }

    public List<PetDTO> getAllPets(String username) {
        var customer = customerRepository.findByUsersId(usersRepository.findByUsername(username).getId());
        return customer
                .getPetList()
                .stream()
                .map(petMapper::petToDto)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders(String username) {
        var customer = customerRepository.findByUsersId(usersRepository.findByUsername(username).getId());
        return customer
                .getOrderList()
                .stream()
                .map(orderMapper::orderToDto)
                .collect(Collectors.toList());
    }

    public void deletePet(PetDTO petDTO) {
        petRepository.delete(petMapper.dtoToPet(petDTO));
    }

    public Order createOrder(OrderDTO orderDTO) {
        return orderRepository.save(orderMapper.dtoToOrder(orderDTO));
    }
}
