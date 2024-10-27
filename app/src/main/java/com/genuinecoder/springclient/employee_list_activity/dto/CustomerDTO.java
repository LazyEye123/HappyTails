package com.genuinecoder.springclient.employee_list_activity.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomerDTO {
    Long id;
    String firstName;
    String lastName;
    Date birthdate;
    String phone;
    String email;
    String addressWalk;
    String photoPath;
    String login;
    String password;
}
