package com.happytails.springserver;

import com.happytails.springserver.models.Employee;
import com.happytails.springserver.service.EmployeeService;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SpringServerApplicationTests {

  @Autowired
  private EmployeeService employeeService;

  @BeforeAll
  public void clear() {
    List<Employee> employees = employeeService.getAllEmployees();
    for (Employee employee : employees) {
      employeeService.delete(employee.getId());
    }
  }

  @Test
  void addEmployeeTest() {
    addEmployee("Bruce Wayne", "Building-X", "Security");
    addEmployee("Harvey Dent", "Building-2", "Police");
    addEmployee("Rachel", "Building-11", "IT");
  }

  private void addEmployee(String name, String location, String branch) {
    Employee employee = new Employee();
    employee.setName(name);
    employee.setLocation(location);
    employee.setBranch(branch);
    employeeService.save(employee);
  }

}
