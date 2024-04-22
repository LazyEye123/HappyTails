package com.happytails.springserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    String firstName;
    String lastName;
    Date birthdate;
    String phone;
    String email;
    String address;
    String about;
    Integer experience;
    Double rating;
    Integer reviewCount;
    Double price;
    Boolean[] orderTypes;
    Boolean isCat;
    Boolean isDog;
}
