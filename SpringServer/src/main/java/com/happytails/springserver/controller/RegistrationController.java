package com.happytails.springserver.controller;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.EmployeeDTO;
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
    public void createEmployee(@Valid @RequestBody EmployeeDTO employee) throws UserAlreadyExsistException {
        if (customerService.checkIsUserNotExists(employee.getLogin())) {
            employeeService.save(employee);
        }
    }

    @PostMapping("/customer")
    public void createCustomer(@Valid @RequestBody CustomerDTO customer) throws UserAlreadyExsistException {
        if (customerService.checkIsUserNotExists(customer.getLogin())) {
            customerService.save(customer);
        }
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
