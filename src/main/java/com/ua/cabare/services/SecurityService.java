package com.ua.cabare.services;

import com.ua.cabare.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class SecurityService {

  @Autowired
  private EmployeeService employeeService;

  public Employee getEmployeeFromSession() {
//    String login = SecurityContextHolder.getContext().getAuthentication().getName();
//    return employeeService.getEmployeeByLogin(login);
    return null;
  }
}
