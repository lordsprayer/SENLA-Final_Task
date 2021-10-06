package com.senla.courses.service;

import com.senla.courses.dto.UserDtoInput;
import com.senla.courses.dto.UserDtoOutput;
import com.senla.courses.dto.UserDtoUpdate;
import com.senla.courses.mapper.UserInputMapper;
import com.senla.courses.mapper.UserOutputMapper;
import com.senla.courses.model.Role;
import com.senla.courses.model.Shop;
import com.senla.courses.model.User;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.api.exception.ServiceException;
import com.senla.courses.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserService implements IUserService, ConstantUtil {

    private final UserRepository userRepository;
    private final UserInputMapper mapperInput = Mappers.getMapper(UserInputMapper.class);
    private final UserOutputMapper mapperOutput = Mappers.getMapper(UserOutputMapper.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UserDtoOutput> getAllUsers(Pageable pageable) {
        try {
            Page<User> users = userRepository.findAll(pageable);
            return users.getContent().stream().map(mapperOutput::userToUserDtoOutput).collect(Collectors.toList());
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public UserDtoOutput getUserById(Integer id) {
        try {
            User user = userRepository.getById(id);
            return mapperOutput.userToUserDtoOutput(user);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public boolean saveUser(UserDtoInput userDtoInput) {
        try {
            User userFromDB = userRepository.findUserByLogin(userDtoInput.getLogin());
            if (userFromDB != null) {
                return false;
            }
            User user = mapperInput.userDtoInputToUser(userDtoInput);
            user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            if (userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
            } else {
                log.log(Level.WARN, SEARCH_ERROR);
            }
        } catch (Exception e) {
            log.log(Level.WARN, DELETING_ERROR);
            throw new ServiceException(DELETING_ERROR, e);
        }
    }

    @Override
    public void updateUser(Integer id, UserDtoUpdate user) {
        try {
            User userFromDB = userRepository.getById(id);
            userFromDB.setName(user.getName());
            userFromDB.setSurname(user.getSurname());
            userFromDB.setPhone(user.getPhone());
            userRepository.save(userFromDB);
        } catch (Exception e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }

    @Override
    public void updateCurrentUser(UserDtoUpdate user, String username) {
        try {
            User userFromDB = userRepository.findUserByLogin(username);
            userFromDB.setName(user.getName());
            userFromDB.setSurname(user.getSurname());
            userFromDB.setPhone(user.getPhone());
            userRepository.save(userFromDB);
        } catch (Exception e) {
            log.log(Level.WARN, UPDATING_ERROR);
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }

    @Override
    public void setUserRoleAdmin(Integer id) {
        try {
            User userFromDB = userRepository.getById(id);
            Set<Role> roles = new HashSet<>() ;
            roles.add(new Role(1, "ROLE_USER"));
            roles.add(new Role(2, "ROLE_ADMIN"));
            userFromDB.setRoles(roles);
            userRepository.save(userFromDB);
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
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
