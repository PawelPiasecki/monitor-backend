package com.monitor.security.jwt;

/**
 * Created by grusz on 02.01.2017.
 */
public class AccountCredentials {

    private String username;
    private String password;

    String getUsername() { return username; }
    String getPassword() { return password; }

    public void setUsername(String _username) { this.username = _username; }
    public void setPassword(String _password) { this.password = _password; }
}