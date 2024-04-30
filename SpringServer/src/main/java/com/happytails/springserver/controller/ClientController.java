package com.happytails.springserver.controller;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.filter.EmployeeFilter;
import com.happytails.springserver.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
public class ClientController {
    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/filter")
    public List<EmployeeDTO> getEmployeesByFilter(@RequestBody EmployeeFilter employeeFilter) {
        return employeeService.getEmployeesByFilter(employeeFilter);
    }

    @PutMapping("/employee/rate")
    public void rateEmployee(@Valid @RequestBody Employee employee, @RequestParam("rating") Integer rating) {
        employeeService.rateEmployee(employee, rating);
    }
}
