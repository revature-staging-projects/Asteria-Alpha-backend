package com.revature.controllers;

import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService user_service;

    @Autowired
    public UserController(final UserService user_service) {
        this.user_service = user_service;
    }
}
