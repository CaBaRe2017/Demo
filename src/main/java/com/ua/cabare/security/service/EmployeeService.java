package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;
import com.ua.cabare.security.dto.EmployeeDto;
import com.ua.cabare.security.errors.EmployeeAlreadyExistException;

public interface EmployeeService {

  Employee registerNewEmployeeAccount(EmployeeDto employeeDto) throws EmployeeAlreadyExistException;

  /* void saveRegisteredEmployee(Employee employee);

   Employee findEmployeeByEmail(String email);
 */
  String validateVerificationToken(String token);

  void createVerificationTokenForEmployee(Employee employee, String token);

  Employee getEmployee(String verificationToken);

}
