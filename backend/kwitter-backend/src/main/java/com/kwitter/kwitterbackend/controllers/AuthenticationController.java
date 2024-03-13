package com.kwitter.kwitterbackend.controllers;

import com.kwitter.kwitterbackend.exceptions.EmailAlreadyTakenException;
import com.kwitter.kwitterbackend.models.ApplicationUser;
import com.kwitter.kwitterbackend.models.RegistrationObject;
import com.kwitter.kwitterbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<String> HandleEmailTaken(EmailAlreadyTakenException e) {
            return new ResponseEntity<String>("The email you provided is already in use",HttpStatus.CONFLICT);
    }

    // go to http://localhost:8080/auth/register
    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationObject ro) {
        return userService.registerUser(ro);
    }
}
