package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.models.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee dtoToEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO employeeToDto(Employee employee);
}
