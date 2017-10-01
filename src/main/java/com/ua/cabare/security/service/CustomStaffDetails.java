package com.ua.cabare.security.service;

import com.ua.cabare.models.Staff;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

public class CustomStaffDetails extends Staff implements UserDetails {

  public CustomStaffDetails(final Staff staff) {
    super(staff);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public String getUsername() {
    return super.getLogin();
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
