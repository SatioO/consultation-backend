package com.accion.consultation.models;

import com.accion.consultation.entities.PersonName;
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
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user) {
        log.info("Entering in Custom User Details");

        PersonName personName = new PersonName();
        personName.setGivenName(user.getName().getGivenName());
        personName.setMiddleName(user.getName().getMiddleName());
        personName.setFamilyName(user.getName().getFamilyName());

        this.setUserId(user.getUserId());
        this.setName(personName);
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setRoles(user.getRoles());
        this.setStatus(user.getStatus());

        List<GrantedAuthority> auths = new ArrayList<>();
        this.getRoles().forEach(role -> {
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

}
