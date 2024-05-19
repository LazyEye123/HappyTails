package com.happytails.springserver.dto;

import com.happytails.springserver.enums.OrderStatus;
import com.happytails.springserver.enums.PaymentMethod;
import com.happytails.springserver.enums.ServiceType;
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
    Date date;
    PaymentMethod paymentMethod;
    OrderStatus orderStatus;
    @Positive
    Integer sum;
    Long clientId;
    Long petId;
    Long employeeId;
}
