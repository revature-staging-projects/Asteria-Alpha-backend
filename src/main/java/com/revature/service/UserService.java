package com.revature.service;

import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo user_repo;

    @Autowired
    public UserService(final UserRepo user_repo) {
        this.user_repo = user_repo;
    }

}
