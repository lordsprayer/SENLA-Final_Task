package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.couses.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class.getName());
    private final IUserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        log.log(Level.INFO, "Received post request: /registration");
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            return ResponseEntity.ok("Passwords don't match");
        }
        if (!userService.saveUser(user)){
            return ResponseEntity.ok("A user with this name already exists");
        }

        return ResponseEntity.ok("The user is registered successfully");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        log.log(Level.INFO, "Received get request: /users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.log(Level.INFO, "Received delete request: /users/" + id);
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/users")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        log.log(Level.INFO, "Received update request: /users/");
        userService.updateUser(user);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        log.log(Level.INFO, "Received get request: /users/" + id );
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDetails> getUserByUsername(@RequestParam String username) {
        log.log(Level.INFO, "Received get request: /users?username=" + username );
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }
}
