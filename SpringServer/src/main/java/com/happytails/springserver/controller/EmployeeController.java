package com.happytails.springserver.controller;

import com.happytails.springserver.model.employee.Employee;
import com.happytails.springserver.model.employee.EmployeeDao;
import java.util.List;

import com.happytails.springserver.model.employee.EmployeeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeDao employeeDao;

  @GetMapping("/employee/get-all")
  public List<Employee> getAllEmployees() {
    return employeeDao.getAllEmployees();
  }
  @GetMapping("/employee/get-all-by-filter")
  public List<Employee> getEmployeesByFilter(@RequestBody EmployeeFilter employeeFilter) {
    return employeeDao.getEmployeesByFilter(employeeFilter);
  }
  @PostMapping("/employee/save")
  public Employee save(@RequestBody Employee employee) {
    return employeeDao.save(employee);
  }
}
