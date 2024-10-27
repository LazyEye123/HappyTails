package com.genuinecoder.springclient.pets_page_activity.model;

import com.genuinecoder.springclient.pets_page_activity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetCard {

    private Long id;
    private String name;
    private String type;
    private Gender gender;
    private Integer age;
    private Double weight;
    private String breed;
    private String description;
    private String photoPath;
    private Long ownerId;

}
