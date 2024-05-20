package com.happytails.springserver.controller;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.mapper.CustomerMapperImpl;
import com.happytails.springserver.mapper.EmployeeMapperImpl;
import com.happytails.springserver.service.CustomerService;
import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.validation.UserAlreadyExsistException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/registration")
public class RegistrationController {
    private final EmployeeService employeeService;
    private final CustomerService customerService;

    @PostMapping("/employee")
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employee) throws UserAlreadyExsistException {
        if (customerService.checkIsUserNotExists(employee.getLogin())) {
            return employeeService.save(employee);
        }
        return null;
    }

    @PostMapping("/customer")
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customer) throws UserAlreadyExsistException {
        if (customerService.checkIsUserNotExists(customer.getLogin())) {
            return customerService.save(customer);
        }
        return null;
    }

    @PostMapping("/location")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getLocation(@RequestBody CustomerDTO customerDTO) throws IOException, GeoIp2Exception {
        var response = customerService.getUserLocation(customerDTO.getAddressWalk());
        return CustomerDTO
                .builder()
                .addressWalk(response
                        .getCity()
                        .getNames()
                        .get("ru"))
                .build();
    }
}
