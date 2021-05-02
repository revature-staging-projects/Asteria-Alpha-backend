package com.revature.service;

import com.revature.Exceptions.AlreadyExistingUserException;
import com.revature.Exceptions.InvalidRequestException;
import com.revature.Exceptions.ResourceNotFoundException;
import com.revature.dto.Credentials;
import com.revature.dto.PrincipalDTO;
import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.util.jwt.JwtGenerator;
import com.revature.util.jwt.JwtParser;
import com.revature.util.encryption.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo user_repo;
    private final JwtParser parser;
    private final JwtGenerator generator;
    private final PasswordEncryption encryption;

    @Autowired
    public UserService(final PasswordEncryption encryption,final UserRepo user_repo,final JwtParser parser, final JwtGenerator generator) {
        this.user_repo  = user_repo;
        this.parser     = parser;
        this.generator  = generator;
        this.encryption = encryption;
    }

    public boolean checkIfValid(final String username) {
        return user_repo.getIfValid(username);
    }

    private boolean isUserConfirmed(final User user) {
        return user_repo.getIfValid(user.getUsername());
    }

    private boolean checkEmptyString(final String str) {
        return str == null || str.isEmpty();
    }

    private Optional<User> authenticateUser(final Credentials creds) {
        if(checkEmptyString(creds.getUsername()) || checkEmptyString(creds.getPassword())) {
            throw new InvalidRequestException("username and password cannot be empty or null.");
        }
        final List<User> users = user_repo.findByUsername(creds.getUsername());
        if( users == null || users.size() < 1) {
            throw new ResourceNotFoundException("no user found.");
        }
        final User user = users.get(0);
        if(!encryption.verifyPassword(creds.getPassword(),user.getPassword())) {
            throw new InvalidRequestException("Invalid Credentials.");
        }
        if(!isUserConfirmed(user)) {
            throw new InvalidRequestException("Account is not confirmed.");
        }
        return Optional.of(user);
    }


    private PrincipalDTO parseUserIntoPrincipal(final User user) {
        final PrincipalDTO principal = new PrincipalDTO();
        principal.setUsername(user.getUsername());
        return principal;
    }

    public Optional<PrincipalDTO> logInUser(final Credentials creds, final HttpServletResponse response) {
        Optional<User> user = authenticateUser(creds);
        if(user.isPresent()) {
            final PrincipalDTO principal = parseUserIntoPrincipal(user.get());
            principal.setToken(generator.generateJwt(principal));
            response.addHeader("Asteria-header",principal.getToken());
            response.setStatus(418);
            return Optional.of(principal);
        }
        return Optional.empty();
    }


    public void registerNewUser(final User  new_user) {
        final List<User> users = user_repo.findByUsername(new_user.getUsername());
        if(users == null || users.size() < 1) {
            user_repo.save(new_user);
            user_repo.addToVerifiedTable(new_user.getUsername());
        }
        else {
            throw new AlreadyExistingUserException("sorry, but: " + new_user.getUsername() + " already exists.");
        }
    }

}
