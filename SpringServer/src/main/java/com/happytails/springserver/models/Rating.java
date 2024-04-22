package com.happytails.springserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rating")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    private Long id;
    @Column(name = "one")
    private int one;
    @Column(name = "two")
    private int two;
    @Column(name = "three")
    private int three;
    @Column(name = "four")
    private int four;
    @Column(name = "five")
    private int five;

    @JsonIgnore
    @OneToOne(mappedBy = "rate")
    private Employee employee;
}
