package com.zyra.api.controller;

import com.zyra.api.model.User;
import com.zyra.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public User login(@RequestBody User request) {
        return service.getByEmail(request.getEmail())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .orElse(null);
    }
}