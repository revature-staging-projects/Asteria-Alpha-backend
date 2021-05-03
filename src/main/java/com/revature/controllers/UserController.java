package com.revature.controllers;

import com.revature.Exceptions.NoSuchUserException;
import com.revature.dto.Credentials;
import com.revature.dto.PrincipalDTO;
import com.revature.models.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService user_service;

    @Autowired
    public UserController(final UserService user_service) {
        this.user_service = user_service;
    }

    @PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PrincipalDTO logUserIn(@RequestBody @Valid final Credentials creds, final HttpServletResponse response) {
        return user_service.logInUser(creds,response).orElseThrow(NoSuchUserException::new);

    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void registerNewUser(@RequestBody final User new_user) {
        user_service.registerNewUser(new_user);
    }
}
