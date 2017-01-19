package com.monitor.model;

import javax.persistence.*;

/**
 * Created by grusz on 19.01.2017.
 */
@Entity
public class SystemUser {
    @Id

    private int id;
    private String name;
    private String login;
    private String passwordHash;
    private String passwordSalt;

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PasswordHash")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "PasswordSalt")
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SystemUser systemUser = (SystemUser) o;

        if (id != systemUser.id) return false;
        if (name != null ? !name.equals(systemUser.name) : systemUser.name != null) return false;
        if (login != null ? !login.equals(systemUser.login) : systemUser.login != null) return false;
        if (passwordHash != null ? !passwordHash.equals(systemUser.passwordHash) : systemUser.passwordHash != null) return false;
        if (passwordSalt != null ? !passwordSalt.equals(systemUser.passwordSalt) : systemUser.passwordSalt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (passwordSalt != null ? passwordSalt.hashCode() : 0);
        return result;
    }
}
