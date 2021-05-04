package com.revature.dto.users;

/**
 * DTO which handles sending user credentials from front end to the backend.
 */
public class Credentials {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
