package com.github.jntakpe.pp.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Bean représentant un utilisateur de l'application
 *
 * @author jntakpe
 */
public class User {

    private String login;

    private Collection<? extends GrantedAuthority> authorities;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
