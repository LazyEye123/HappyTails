package com.happytails.springserver.model.employee;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "animaltypes")
@Data
public class AnimalTypes
{
    boolean cat;
    boolean dog;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int animaltypes_id;

    @OneToOne(mappedBy = "animalTypes")
    private Employee employee;

}
