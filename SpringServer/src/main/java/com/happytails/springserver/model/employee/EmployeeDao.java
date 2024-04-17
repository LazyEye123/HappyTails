package com.happytails.springserver.model.employee;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDao {

  @Autowired
  private EmployeeRepository repository;

  public Employee save(Employee employee) {
    return repository.save(employee);
  }

  public List<Employee> getAllEmployees() {
    List<Employee> employees = new ArrayList<>();
    Streamable.of(repository.findAll())
        .forEach(employees::add);
    return employees;
  }
  public List<Employee> getEmployeesByFilter(EmployeeFilter employeeFilter) {
    List<Employee> employees = new ArrayList<>();
    switch (employeeFilter.priority) {
      case Price -> {
        Streamable.of(repository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByOrderPrices_WalkingDescOrderPrices_FurloughDescOrderPrices_DogsitterDesc(employeeFilter.city, employeeFilter.cat, employeeFilter.dog, employeeFilter.orderTypes[0], employeeFilter.orderTypes[1], employeeFilter.orderTypes[2]))
                .forEach(employees::add);
      }
      case Review -> {
        Streamable.of(repository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByReviewCountAsc(employeeFilter.city, employeeFilter.cat, employeeFilter.dog, employeeFilter.orderTypes[0], employeeFilter.orderTypes[1], employeeFilter.orderTypes[2]))
                .forEach(employees::add);
      }
      case Rating -> {
        Streamable.of(repository.findAllByAddressAndAnimalTypes_CatAndAnimalTypes_DogAndOrderPrices_DowalkingAndOrderPrices_DofurloughAndOrderPrices_DodogsitterOrderByRatingAsc(employeeFilter.city, employeeFilter.cat, employeeFilter.dog, employeeFilter.orderTypes[0], employeeFilter.orderTypes[1], employeeFilter.orderTypes[2]))
                .forEach(employees::add);
      }
    }
    return employees;
  }
  public void delete(int employeeId) {
    repository.deleteById(employeeId);
  }
}
