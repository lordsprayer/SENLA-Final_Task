package com.senla.courses.controller;

import com.senla.courses.api.service.IUserService;
import com.senla.courses.dto.UserDtoOutput;
import com.senla.courses.dto.UserDtoUpdate;
import com.senla.courses.model.User;
import com.senla.courses.util.PageCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final PageCheck pageCheck;

    @GetMapping()
    public ResponseEntity<List<UserDtoOutput>> getAllUsers(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get request: /users");
        size = pageCheck.checkPage(size);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoOutput> getUserById(@PathVariable Integer id) {
        log.log(Level.INFO, "Received get request: /users" + id);
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.log(Level.INFO, "Received delete request: /users/" + id);
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody UserDtoUpdate user) {
        log.log(Level.INFO, "Received update request: /users/");
        userService.updateUser(id, user);
        return ResponseEntity.accepted().build();
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username) {
//        log.log(Level.INFO, "Received get request: /users?username=" + username );
//        return ResponseEntity.ok(userService.loadUserByUsername(username));
//    }

    @PutMapping("/profile/update")
    public ResponseEntity<Void> updateCurrentUser(@RequestBody UserDtoUpdate user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        userService.updateCurrentUser(user, username);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Void> setUserRoleAdmin(@PathVariable Integer id) {
        userService.setUserRoleAdmin(id);
        return ResponseEntity.accepted().build();
    }
    //todo добавить смену пароля, мб с отсылкой на почту
}
