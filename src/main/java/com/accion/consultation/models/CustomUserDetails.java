package com.accion.consultation.models;

import com.accion.consultation.constants.RoleEnum;
import com.accion.consultation.entities.PatientEntity;
import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CustomUserDetails extends UserEntity implements UserDetails {
    private String username;
    private String password;
    private RoleEntity role;
    private PatientEntity patient;

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity byUsername) {
        this.username = byUsername.getUsername();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        this.role = byUsername.getRole();
        auths.add(new SimpleGrantedAuthority(this.role.getName()));
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public RoleEntity getRole() {
        return role;
    }

    @Override
    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }
}
