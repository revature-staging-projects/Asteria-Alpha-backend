package com.revature.controllers;

import com.revature.Exceptions.NoSuchUserException;
import com.revature.dto.Credentials;
import com.revature.dto.PrincipalDTO;
import com.revature.service.UserService;
import com.revature.util.jwt.JwtParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService user_service;
    private final JwtParser parser;

    @Autowired
    public UserController(final UserService user_service, final JwtParser parser) {
        this.user_service = user_service;
        this.parser       = parser;
    }

    @PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PrincipalDTO logUserIn(@RequestBody @Valid final Credentials creds, final HttpServletResponse response) {
        return user_service.logInUser(creds,response).orElseThrow(NoSuchUserException::new);

    }

}
