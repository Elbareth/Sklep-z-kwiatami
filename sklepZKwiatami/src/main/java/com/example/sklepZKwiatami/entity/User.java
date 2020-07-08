package com.example.sklepZKwiatami.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "d_user")
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Column(name = "granted_authority")
    private String grantedAuthority;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;
    @NotNull
    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;
    @NotNull
    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;
    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    public User(Integer id, String login, String password, String grantedAuthority, String name, String surname, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.grantedAuthority = grantedAuthority;
        this.name = name;
        this.surname = surname;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User(String login, String password, String grantedAuthority, String name, String surname, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.login = login;
        this.password = password;
        this.grantedAuthority = grantedAuthority;
        this.name = name;
        this.surname = surname;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getGrantedAuthority() {
        return grantedAuthority;
    }

    public void setGrantedAuthority(String grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isAccountNonExpired() == user.isAccountNonExpired() &&
                isAccountNonLocked() == user.isAccountNonLocked() &&
                isCredentialsNonExpired() == user.isCredentialsNonExpired() &&
                isEnabled() == user.isEnabled() &&
                getId().equals(user.getId()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                getGrantedAuthority().equals(user.getGrantedAuthority()) &&
                getName().equals(user.getName()) &&
                getSurname().equals(user.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getGrantedAuthority(), getName(), getSurname(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", grantedAuthority='" + grantedAuthority + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
