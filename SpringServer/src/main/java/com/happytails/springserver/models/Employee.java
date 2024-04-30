package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.util.List;

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
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @Past
    @Column(name = "birthdate")
    private Date birthdate;
    //    @Pattern(regexp = "8[(]\\d{3}[)]-\\d{3}-\\d{2}-\\d{2}", message = "Пожалуйста, введите номер в указанном формате: 8(XXX)-XXX-XX-XX")
    @Column(name = "phone")
    private String phone;

    @Email
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
    @Column(name = "photo")
    private String photoPath;
    @Column(name = "animal_id")
    private Long animalId;
    @Column(name = "prices_id")
    private Long pricesId;
    @Column(name = "rating_id")
    private Long ratingId;
    @Column(name = "users_id")
    private Long usersId;
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

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Users usersEmployee;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orderList;
}
