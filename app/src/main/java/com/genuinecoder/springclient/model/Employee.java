package com.genuinecoder.springclient.model;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
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
