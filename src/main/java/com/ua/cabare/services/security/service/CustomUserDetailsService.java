package com.ua.cabare.services.security.service;


import com.ua.cabare.models.Employee;
import com.ua.cabare.repositories.EmployeeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Optional<Employee> userOptional = employeeRepository.findByLogin(login);
    userOptional.orElseThrow(() -> new UsernameNotFoundException("Login not found"));
    return userOptional.map(CustomUserDetails::new).get();
  }
}
