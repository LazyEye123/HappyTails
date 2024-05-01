package com.happytails.springserver.dto;

import com.happytails.springserver.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PetDTO {
    Long id;
    @NotBlank(message = "Кличка не может быть пустой")
    String name;
    @NotBlank(message = "Укажите тип питомца")
    String type;
    Gender gender;
    @Positive
    Integer age;
    @Positive
    Integer weight;
    String breed;
    String description;
    String photoPath;
    Long ownerId;
}
