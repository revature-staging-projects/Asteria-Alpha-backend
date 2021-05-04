package com.revature.controllers;

import com.revature.Exceptions.NoSuchUserException;
import com.revature.dto.users.Credentials;
import com.revature.dto.users.PrincipalDTO;
import com.revature.models.users.User;
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

/**
 * Controller for handling request related to user of the website such as logging in and registering an account.
 */
@RestController
public class UserController {

    private final UserService user_service;

    @Autowired
    public UserController(final UserService user_service) {
        this.user_service = user_service;
    }

    /**
     * Method which handles users logging in.
     * @param creds holds the username and password.
     * @param response holds the jwt which is used for identifying the user later on.
     * @return DTO containing username.
     */
    @PostMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public PrincipalDTO logUserIn(@RequestBody @Valid final Credentials creds, final HttpServletResponse response) {
        return user_service.logInUser(creds,response).orElseThrow(NoSuchUserException::new);

    }

    /**
     * Method for registering a user on the site.
     * @param new_user Object holding user information such as username, password, and email address.
     */
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void registerNewUser(@RequestBody final User new_user) {
        user_service.registerNewUser(new_user);
    }

    //TODO set up email for verification

}
