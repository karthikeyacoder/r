package com.klef.fsd.sdp.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.klef.fsd.sdp.model.Admin;
import com.klef.fsd.sdp.model.Customer;
import com.klef.fsd.sdp.model.ServiceProvider;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String role;
    private int userId;

    public CustomUserDetails(Customer customer) {
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.role = "ROLE_USER";
        this.userId = customer.getId();
    }

    public CustomUserDetails(Admin admin) {
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.role = "ROLE_ADMIN";
        this.userId = -1; // Admin has no numeric ID, use -1 or handle accordingly
    }

    public CustomUserDetails(ServiceProvider serviceProvider) {
        this.username = serviceProvider.getUsername();
        this.password = serviceProvider.getPassword();
        this.role = "ROLE_SERVICE_PROVIDER";
        this.userId = serviceProvider.getId();
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // For simplicity, all accounts are enabled, non-expired, non-locked, and credentials non-expired
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
