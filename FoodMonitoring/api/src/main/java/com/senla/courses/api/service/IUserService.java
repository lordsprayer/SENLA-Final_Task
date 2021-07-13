package com.senla.courses.api.service;

import com.senla.courses.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    boolean saveUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);

    void updateCurrentUser(User user, String username);

    void setUserRoleAdmin(Integer id);
}
