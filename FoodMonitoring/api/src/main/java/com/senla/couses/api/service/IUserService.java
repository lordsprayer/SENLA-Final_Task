package com.senla.couses.api.service;

import com.senla.courses.dto.UserDto;
import com.senla.courses.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    boolean saveUser(User user);
    void deleteUser(Integer id);
    void updateUser(Integer id);
}
