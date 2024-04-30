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
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

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
    @Column(name = "address_walk")
    private String addressWalk;
    @Column(name = "photo")
    private String photoPath;
    @Column(name = "users_id")
    private Long usersId;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Pet> petList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orderList;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "users_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Users usersCustomer;
}
