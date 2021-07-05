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

    public UserDto(Integer id, String name, String surname, String login, String password, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}
