package com.happytails.springserver.mapper;

import com.happytails.springserver.dto.EmployeeDTO;
import com.happytails.springserver.models.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-30T19:59:50+0300",
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

        employee.id( employeeDTO.getId() );
        employee.firstName( employeeDTO.getFirstName() );
        employee.lastName( employeeDTO.getLastName() );
        employee.birthdate( employeeDTO.getBirthdate() );
        employee.phone( employeeDTO.getPhone() );
        employee.email( employeeDTO.getEmail() );
        employee.address( employeeDTO.getAddress() );
        employee.rating( employeeDTO.getRating() );
        employee.reviewCount( employeeDTO.getReviewCount() );
        employee.about( employeeDTO.getAbout() );
        employee.experience( employeeDTO.getExperience() );

        return employee.build();
    }

    @Override
    public EmployeeDTO employeeToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder employeeDTO = EmployeeDTO.builder();

        employeeDTO.id( employee.getId() );
        employeeDTO.firstName( employee.getFirstName() );
        employeeDTO.lastName( employee.getLastName() );
        employeeDTO.birthdate( employee.getBirthdate() );
        employeeDTO.phone( employee.getPhone() );
        employeeDTO.email( employee.getEmail() );
        employeeDTO.address( employee.getAddress() );
        employeeDTO.about( employee.getAbout() );
        employeeDTO.experience( employee.getExperience() );
        employeeDTO.rating( employee.getRating() );
        employeeDTO.reviewCount( employee.getReviewCount() );

        return employeeDTO.build();
    }
}
