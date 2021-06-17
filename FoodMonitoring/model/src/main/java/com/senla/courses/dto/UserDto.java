package com.senla.courses.dto;

import com.senla.courses.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    String name;
    String surname;
    private String login;
    private String password;
    String phone;
    private String passwordConfirm;
    private Set<Role> roles;
}
