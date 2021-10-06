package com.senla.courses.api.service;

import com.senla.courses.dto.UserDtoInput;
import com.senla.courses.dto.UserDtoOutput;
import com.senla.courses.dto.UserDtoUpdate;
import com.senla.courses.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    /**
     * Returns list of all users
     *
     * @param pageable pagination information(page number and page size)
     *
     * @return list of all users
     */
    List<UserDtoOutput> getAllUsers(Pageable pageable);

    /**
     * Returns true, if the user is successfully saved in the database
     *
     * @param id id of the desired user
     *
     * @return true, if the user is successfully saved in the database
     */
    UserDtoOutput getUserById(Integer id);

    /**
     * Returns true, if the user is successfully saved in the database
     *
     * @param user saved user ({@link User})
     *
     * @return true, if the user is successfully saved in the database
     */
    boolean saveUser(UserDtoInput user);

    /**
     * Deletes a user from the database
     *
     * @param id user id
     */
    void deleteUser(Integer id);

    /**
     * Updates a user in the database
     *
     * @param user updated user ({@link User})
     */
    void updateUser(Integer id, UserDtoUpdate user);

    /**
     * Updates the data of the authenticated user
     *
     * @param user authenticated user
     * @param username username of authenticated user
     */
    void updateCurrentUser(UserDtoUpdate user, String username);

    /**
     * Gives the user administrator rights
     *
     * @param id user id
     */
    void setUserRoleAdmin(Integer id);
}
