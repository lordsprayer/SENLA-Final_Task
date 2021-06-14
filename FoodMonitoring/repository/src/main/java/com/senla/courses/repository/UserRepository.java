package com.senla.courses.repository;

import com.senla.courses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
}
