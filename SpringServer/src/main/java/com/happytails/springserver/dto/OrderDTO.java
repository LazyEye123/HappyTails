package com.happytails.springserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happytails.springserver.enums.OrderStatus;
import com.happytails.springserver.enums.PaymentMethod;
import com.happytails.springserver.enums.ServiceType;
import com.happytails.springserver.models.Customer;
import com.happytails.springserver.models.Employee;
import com.happytails.springserver.models.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDTO {
    Long id;
    ServiceType serviceType;
    @Past
    Date startDate;
    Date endDate;
    String name;
    String phone;
    String address;
    PaymentMethod paymentMethod;
    OrderStatus orderStatus;
    @Positive
    Integer sum;
    Long clientId;
    Long petId;
    Long employeeId;
    Pet pet;
    Customer customer;
    Employee employee;
}
