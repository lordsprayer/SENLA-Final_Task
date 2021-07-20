package com.senla.courses.controller;

import com.senla.courses.model.User;
import com.senla.courses.api.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestParam Integer page, Integer size) {
        log.log(Level.INFO, "Received get request: /users");
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        log.log(Level.INFO, "Received delete request: /users/" + id);
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

    @PutMapping()
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        log.log(Level.INFO, "Received update request: /users/");
        userService.updateUser(user);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username) {
        log.log(Level.INFO, "Received get request: /users?username=" + username );
        return ResponseEntity.ok(userService.loadUserByUsername(username));
    }

    @PutMapping("/profile/update")
    public ResponseEntity<Void> updateCurrentUser(@RequestBody User user) {
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
}
