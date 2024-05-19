package com.happytails.springserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDTO {
    Long id;
    @NotBlank(message = "Имя не может быть пустым")
    String firstName;
    @NotBlank(message = "Фамилия не может быть пустой")
    String lastName;
    @Past
    Date birthdate;
    @Pattern(regexp = "8[(]\\d{3}[)]-\\d{3}-\\d{2}-\\d{2}", message = "Пожалуйста, введите номер в указанном формате: 8(XXX)-XXX-XX-XX")
    String phone;
    @Email
    String email;
    String address;
    String about;
    Integer experience;
    Double rating;
    Integer reviewCount;
    List<Double> prices;
    String photoPath;
    List<Boolean> orderTypes;
    Boolean isCat;
    Boolean isDog;
    Integer ratingValue;
    @NotBlank(message = "Поле логина не должно быть пустым")
    String login;
    @Size(min = 8, message = "Длина пароля должна быть не менее 8 символов")
    String password;
}
