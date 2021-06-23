package com.senla.couses.api.service;

import com.senla.courses.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    boolean saveUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
}
