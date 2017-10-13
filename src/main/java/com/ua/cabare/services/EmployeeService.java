package com.ua.cabare.services;

import com.ua.cabare.exceptions.EmployeeNotFoundException;
import com.ua.cabare.models.Employee;
import com.ua.cabare.repositiries.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
    return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
  }

  public Employee getEmployeeByLogin(String login) throws EmployeeNotFoundException {
    return employeeRepository.findByLogin(login).orElseThrow(() -> new EmployeeNotFoundException());
  }
}
