package com.example.sklepZKwiatami.dto;

import java.util.Objects;

public class LoginUserDTO {
    private String login;
    private String password;

    public LoginUserDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginUserDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginUserDTO)) return false;
        LoginUserDTO that = (LoginUserDTO) o;
        return getLogin().equals(that.getLogin()) &&
                getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
