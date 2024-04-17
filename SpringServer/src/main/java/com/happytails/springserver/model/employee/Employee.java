package com.happytails.springserver.model.employee;

import javax.persistence.*;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int employeeid;

  private String login;
  private String password;
  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;
  private Date birthdate;
  private String phone;

  private String email;
  private String address;
  private double rating;

  @Column(name = "reviewcount")
  private int reviewCount;
  private String about;
  private int experience;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "animaltypes_FK", referencedColumnName = "animaltypes_id")
  private AnimalTypes animalTypes;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "orderprices_FK", referencedColumnName = "orderprices_id")
  private OrderPrices orderPrices;


  @Override
  public String toString() {
    return "Employee{" +
        "employeeid=" + employeeid +
        ", login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
            ", birthdate='" + birthdate + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", rating='" + rating + '\'' +
            ", reviewCount='" + reviewCount + '\'' +
            ", about='" + about + '\'' +
            ", experience='" + experience + '\'' +
        '}';
  }
}
