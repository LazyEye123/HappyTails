package com.genuinecoder.springclient.employee_list_activity.mapper;

import com.genuinecoder.springclient.employee_list_activity.dto.EmployeeDTO;
import com.genuinecoder.springclient.employee_list_activity.model.EmployeeCard;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapperImpl {

    public EmployeeCard dtoToEmployee(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        EmployeeCard.EmployeeCardBuilder employee = EmployeeCard.builder();

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
        employee.photoPath( employeeDTO.getPhotoPath() );
        employee.isCat( employeeDTO.getIsCat() );
        employee.isDog( employeeDTO.getIsDog() );
        employee.orderTypes( employeeDTO.getOrderTypes() );
        employee.price(employeeDTO.getPrice());


        return employee.build();
    }

    public List<EmployeeCard> dtoListToEmployeeList(List<EmployeeDTO> employeeDTOList)
    {
        List<EmployeeCard> employeeCardList = new ArrayList<>();
        for(EmployeeDTO dto : employeeDTOList)
        {
            employeeCardList.add(dtoToEmployee(dto));
        }
        return employeeCardList;
    }
    public EmployeeDTO employeeToDto(EmployeeCard employee) {
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
        employeeDTO.photoPath( employee.getPhotoPath() );

        return employeeDTO.build();
    }
}

