package com.genuinecoder.springclient.employee_list_activity.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    Long id;
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
    String photoPath;
    Boolean[] orderTypes;
    Boolean isCat;
    Boolean isDog;
    Integer ratingValue;
    String login;
    String password;
}
