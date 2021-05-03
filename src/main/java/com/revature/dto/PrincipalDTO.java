package com.revature.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrincipalDTO {

    @JsonIgnore
    private String token;

    private String username;

    private String email;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
