package com.genuinecoder.springclient.pets_page_activity.dto;

import com.genuinecoder.springclient.pets_page_activity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PetDTO {
    Long id;
    String name;
    String type;
    Gender gender;
    Integer age;
    Double weight;
    String breed;
    String description;
    String photoPath;
    Long ownerId;
}