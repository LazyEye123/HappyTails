package com.happytails.springserver.controller;

import com.happytails.springserver.service.EmployeeService;
import com.happytails.springserver.filter.EmployeeFilter;
import com.happytails.springserver.models.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0")
// ко всем маппингам добавил /api/v1.0
public class ClientController {
    private final EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/filter")
    public List<Employee> getEmployeesByFilter(@RequestBody EmployeeFilter employeeFilter) {
        return employeeService.getEmployeesByFilter(employeeFilter);
    }

    @PutMapping("/employee/rate")
    public Employee rateEmployee(@RequestBody Employee employee, @RequestParam("rating") Integer rating) {
        return employeeService.rateEmployee(employee, rating);
    }
}
