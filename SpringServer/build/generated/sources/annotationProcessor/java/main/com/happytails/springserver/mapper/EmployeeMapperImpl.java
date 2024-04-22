package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.models.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T22:53:32+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 18.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee dtoToEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.firstName( employeeDTO.getFirstName() );
        employee.lastName( employeeDTO.getLastName() );
        employee.address( employeeDTO.getAddress() );
        employee.rating( employeeDTO.getRating() );
        employee.reviewCount( employeeDTO.getReviewCount() );

        return employee.build();
    }

    @Override
    public EmployeeDTO employeeToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.firstName( employee.getFirstName() );
        employeeDTO.lastName( employee.getLastName() );
        employeeDTO.address( employee.getAddress() );
        employeeDTO.rating( employee.getRating() );
        employeeDTO.reviewCount( employee.getReviewCount() );

        return employeeDTO.build();
    }
}
