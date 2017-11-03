package com.ua.cabare.services;

import static com.ua.cabare.domain.ResponseStatus.EMPLOYEE_NOT_FOUND;

import com.ua.cabare.models.Employee;
import com.ua.cabare.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND));
  }

  public Employee getEmployeeByLogin(String login) {
    return employeeRepository.findByLogin(login)
        .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND));
  }
}
