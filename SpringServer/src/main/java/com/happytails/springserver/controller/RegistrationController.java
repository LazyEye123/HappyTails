package com.happytails.springserver.controller;

import com.happytails.springserver.dto.CustomerDTO;
import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.service.CustomerService;
import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.validation.UserAlreadyExsistException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
