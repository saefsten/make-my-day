package com.mmd.MakeMyDay;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class SecurityUserPrincipal implements UserDetails {
    private User user;

    public SecurityUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleSet) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    // the getPassword method in SecurityUserPrincipal is using the password in our user object
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // the getUsername method in SecurityUserPrincipal is using the username in our user object
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // we need to override these 4 methods since they exist in the interface, we could add columns for these values in the database and variables to delegate to in our user object
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
