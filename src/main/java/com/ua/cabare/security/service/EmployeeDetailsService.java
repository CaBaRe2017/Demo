package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;
import com.ua.cabare.models.Role;
import com.ua.cabare.repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class EmployeeDetailsService implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Employee employee = employeeRepository.findByEmail(email);
    try {
      if (employee == null) {
        throw new UsernameNotFoundException("No user found with email: " + email);
      }

      return new User(employee.getEmail(), employee.getPassword(), true, true,
          true, true, getAuthorities(employee.getRoles()));

    } catch (Exception ex) {
      throw  new RuntimeException(ex);
    }
  }

  private List<GrantedAuthority> getAuthorities(Set<Role> roles) {
    List<GrantedAuthority> roleList = new ArrayList<>();
    for (Role role: roles){
      roleList.add(new SimpleGrantedAuthority(role.getName()));
    }
    return roleList;
  }
}
