package com.example.eshop.controller;

import com.example.eshop.registration.RegistrationRequest;
import com.example.eshop.registration.RegistrationService;
import com.example.eshop.service.UserServiceImpl;
import com.example.eshop.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/v2/registration")
@RestController
@AllArgsConstructor
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
