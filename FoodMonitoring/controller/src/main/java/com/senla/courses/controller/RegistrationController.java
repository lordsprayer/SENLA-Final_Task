package com.senla.courses.controller;

import com.senla.courses.api.service.IUserService;
import com.senla.courses.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final IUserService userService;

    @PostMapping()
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
}
