package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_prices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPrices {
    @Column(name = "walking_price")
    double walkingPrice;
    @Column(name = "furlough_price")
    double furloughPrice;
    @Column(name = "dogsitter_price")
    double dogsitterPrice;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "orderPrices")
    private Employee employee;
}
