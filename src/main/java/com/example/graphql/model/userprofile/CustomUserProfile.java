package com.example.graphql.model.userprofile;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserProfile extends UserProfile implements UserDetails {


    public CustomUserProfile(UserProfile userProfile) {
        super(userProfile);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roleDetails = getRoleDetailSet()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()))
                .collect(Collectors.toList());
        if (roleDetails.isEmpty()) {
            throw new InternalAuthenticationServiceException("No Role Found");
        }
        return roleDetails;
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        if (!super.getActive()) {
            throw new InternalAuthenticationServiceException("User is not Active");
        }
        return super.getActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        if (!super.getActive()) {
            throw new InternalAuthenticationServiceException("User is not Active");
        }
        return super.getActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (!super.getActive()) {
            throw new InternalAuthenticationServiceException("User is not Active");
        }
        return super.getActive();
    }

    @Override
    public boolean isEnabled() {
        if (!super.getActive()) {
            throw new InternalAuthenticationServiceException("User is not Active");
        }
        return super.getActive();
    }
}
