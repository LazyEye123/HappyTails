package com.happytails.springserver.model.employee;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orderprices")
@Data
public class OrderPrices
{
    double walking;
    double furlough;
    double dogsitter;
    boolean dowalking;
    boolean dofurlough;
    boolean dodogsitter;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderprices_id;

    @OneToOne(mappedBy = "orderPrices")
    private Employee employee;
}
