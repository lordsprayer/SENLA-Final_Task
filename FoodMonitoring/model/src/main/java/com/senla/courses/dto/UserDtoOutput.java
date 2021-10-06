package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOutput {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String phone;
    private Set<RoleDto> roles;
}
