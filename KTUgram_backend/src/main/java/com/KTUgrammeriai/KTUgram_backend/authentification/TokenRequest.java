package com.KTUgrammeriai.KTUgram_backend.authentification;

public class TokenRequest {
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
