package com.happytails.springserver.controller;

import com.happytails.springserver.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class EmployeeController {
    private final EmployeeService employeeService;
}
