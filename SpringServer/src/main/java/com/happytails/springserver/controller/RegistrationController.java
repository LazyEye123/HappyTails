package com.happytails.springserver.controller;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/registration")
public class RegistrationController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public void createEmployee(@RequestBody Employee employee) {
         employeeService.save(employee);
    }
}
