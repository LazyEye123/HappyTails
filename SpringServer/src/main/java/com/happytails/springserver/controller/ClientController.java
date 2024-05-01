package com.happytails.springserver.controller;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.dto.PetDTO;
import com.happytails.springserver.service.CustomerService;
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
    private final CustomerService customerService;

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/filter")
    public List<EmployeeDTO> getEmployeesByFilter(@RequestBody EmployeeFilter employeeFilter) {
        return employeeService.getEmployeesByFilter(employeeFilter);
    }

    @PutMapping("/employee/rate")
    public void rateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.rateEmployee(employeeDTO);
    }

    @PostMapping("/pet")
    public void addPet(@Valid @RequestBody PetDTO petDTO) {
        customerService.savePet(petDTO);
    }
}
