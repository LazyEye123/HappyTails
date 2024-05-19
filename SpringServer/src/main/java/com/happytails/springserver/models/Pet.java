package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.happytails.springserver.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "pet")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "type")
    private String type;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Positive
    @Column(name = "age")
    private Integer age;
    @Positive
    @Column(name = "weight")
    private Double weight;
    @Column(name = "breed")
    private String breed;
    @Column(name = "description")
    private String description;
    @Column(name = "photo")
    private String photoPath;
    @Column(name = "owner_id")
    private Long ownerId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "owner_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer owner;
    @OneToOne(mappedBy = "pet", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Order order;
}
