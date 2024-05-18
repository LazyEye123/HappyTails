package com.genuinecoder.springclient.employee_list_activity.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCard {
  Long id;
  String firstName;
  String lastName;
  Date birthdate;
  String phone;
  String email;
  String address;
  String about;
  Integer experience;
  String photoPath;
  Double rating;
  Integer reviewCount;
  Double price;
  Boolean[] orderTypes;
  Boolean isCat;
  Boolean isDog;
}
