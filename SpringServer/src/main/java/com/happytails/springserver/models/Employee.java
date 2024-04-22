package com.happytails.springserver.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "employee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "rating")
    private Double rating;

    @Column(name = "review_count")
    private Integer reviewCount;
    @Column(name = "about")
    private String about;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "animal_id")
    private Long animalId;
    @Column(name = "prices_id")
    private Long pricesId;
    @Column(name = "rating_id")
    private Long ratingId;
    // ВРОДЕ КАК починил отношения между таблицами (но это не точно)
    // JsonIgnore добавил, чтобы тело ответа корректно формировалось
    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "animal_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AnimalTypes animalTypes;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "prices_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderPrices orderPrices;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "rating_id", referencedColumnName = "id", insertable = false, updatable = false)
    private OrderPrices rate;
}
