package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;
import com.ua.cabare.security.dto.EmployeeDto;
import com.ua.cabare.security.errors.EmployeeAlreadyExistException;

public interface EmployeeService {

  Employee registerNewEmployeeAccount(EmployeeDto employeeDto) throws EmployeeAlreadyExistException;

  Employee getEmployee(String verificationToken);

}
