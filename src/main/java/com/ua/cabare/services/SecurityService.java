package com.ua.cabare.services;

import com.ua.cabare.exceptions.EmployeeNotFoundException;
import com.ua.cabare.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  @Autowired
  private EmployeeService employeeService;

  public Employee getEmployeeFromSession() throws EmployeeNotFoundException {
    String login = SecurityContextHolder.getContext().getAuthentication().getName();
    return employeeService.getEmployeeByLogin(login);
  }
}
