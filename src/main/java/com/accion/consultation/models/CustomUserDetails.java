package com.accion.consultation.models;

import com.accion.consultation.entities.RoleEntity;
import com.accion.consultation.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class CustomUserDetails extends UserEntity implements UserDetails {
    private String username;
    private String password;
    private List<RoleEntity> roles;

    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity byUsername) {
        log.info("Entering in Custom User Details");
        this.username = byUsername.getUsername();
        this.password= byUsername.getPassword();
        this.roles = byUsername.getRoles();

        List<GrantedAuthority> auths = new ArrayList<>();
        this.roles.forEach(role -> {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        });

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
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public List<RoleEntity> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}
