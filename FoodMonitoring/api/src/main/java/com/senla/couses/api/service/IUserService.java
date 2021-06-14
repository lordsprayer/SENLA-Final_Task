package com.senla.couses.api.service;

import com.senla.courses.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAll();
    User getById(Integer id);
    boolean save(User user);
    void delete(Integer id);
}
