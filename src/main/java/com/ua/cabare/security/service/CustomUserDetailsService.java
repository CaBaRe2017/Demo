package com.ua.cabare.security.service;


import com.ua.cabare.models.Staff;
import com.ua.cabare.security.repository.UsersRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Optional<Staff> userOptional = usersRepository.findByLogin(login);
    userOptional.orElseThrow(() -> new UsernameNotFoundException("Login not found"));
    return userOptional.map(CustomStaffDetails::new).get();
  }
}
