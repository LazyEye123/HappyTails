package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "animal_types")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalTypes
{
    @Column(name = "cat")
    boolean cat;
    @Column(name = "dog")
    boolean dog;

    @Id
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "animalTypes")
    private Employee employee;

}
