package com.happytails.springserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    String firstName;
    String lastName;
    String address;
    Double rating;
    Integer reviewCount;
    Double price;
    Boolean[] orderTypes;
    Boolean isCat;
    Boolean isDog;
}
