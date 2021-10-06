package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoInput {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private String passwordConfirm;

    public UserDtoInput(Integer id, String name, String surname, String login, String password, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public UserDtoInput(String name, String surname, String login, String password, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }
}
