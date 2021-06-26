package com.senla.courses.service;

import com.senla.courses.model.Role;
import com.senla.courses.model.User;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserService extends ConstantUtil implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public User getUserById(Integer id) {
        try {
            return userRepository.getById(id);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public boolean saveUser(User user) {
        try {
            User userFromDB = userRepository.findUserByLogin(user.getUsername());
            if (userFromDB != null) {
                return false;
            }

            user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            log.log(Level.WARN, SEARCH_ERROR);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            User userFromDB = userRepository.getById(user.getId());
            userFromDB.setName(user.getName());
            userFromDB.setSurname(user.getSurname());
            userFromDB.setLogin(user.getLogin());
            userFromDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userFromDB.setPhone(user.getPhone());
            userRepository.save(userFromDB);
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}