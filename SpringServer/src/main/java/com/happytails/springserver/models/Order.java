package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happytails.springserver.enums.OrderStatus;
import com.happytails.springserver.enums.PaymentMethod;
import com.happytails.springserver.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Column(name = "date")
    private Date date;
    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "sum")
    private Integer sum;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "pet_id")
    private Long petId;
    @Column(name = "employee_id")
    private Long employeeId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pet_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Pet pet;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Employee employee;
}
