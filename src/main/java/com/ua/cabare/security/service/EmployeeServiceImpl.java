package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;
import com.ua.cabare.models.Role;
import com.ua.cabare.repositories.DepartmentRepository;
import com.ua.cabare.repositories.EmployeeRepository;
import com.ua.cabare.repositories.PositionRepository;
import com.ua.cabare.repositories.RoleRepository;
import com.ua.cabare.repositories.VerificationTokenRepository;
import com.ua.cabare.security.dto.EmployeeDto;
import com.ua.cabare.security.errors.EmployeeAlreadyExistException;
import com.ua.cabare.security.model.VerificationToken;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final String TOKEN_INVALID = "invalidToken";
  private static final String TOKEN_EXPIRED = "expired";
  private static final String TOKEN_VALID = "valid";

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private PositionRepository positionRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private VerificationTokenRepository verificationTokenRepository;

  @Transactional
  @Override
  public Employee registerNewEmployeeAccount(EmployeeDto employeeDto) {
    if (isEmailExist(employeeDto.getEmail())) {
      throw new EmployeeAlreadyExistException("There is an account with that email address: "
          + employeeDto.getEmail());
    }

    Employee employee = new Employee();
    employee.setName(employeeDto.getName());
    employee
        .setPosition(positionRepository.getPositionById(Long.valueOf(employeeDto.getPosition())));
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findRoleById(Long.valueOf(employeeDto.getRole())));
    employee.setRoles(roles);
    employee.setDepartment(departmentRepository.findDepartmentById(
        Long.valueOf(employeeDto.getDepartment())));
    employee.setStartDay(LocalDate.parse(employeeDto.getStartDay()));
    employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
    employee.setBirthday(LocalDate.parse(employeeDto.getBirthday()));
    employee.setEmail(employeeDto.getEmail());
    employee.setPhone(employeeDto.getPhone());
    return employeeRepository.save(employee);
  }

  @Override
  public String validateVerificationToken(String token) {
    VerificationToken tokenFromDb = verificationTokenRepository.findByToken(token);
    if (tokenFromDb == null) {
      return TOKEN_INVALID;
    }

    Employee employee = tokenFromDb.getEmployee();
    Period period = Period.between(tokenFromDb.getExpiryDate(), LocalDate.now());
    if (period.getDays() <= 0) {
      verificationTokenRepository.delete(tokenFromDb);
      return TOKEN_EXPIRED;
    }

    employee.setEnabled(true);
    employeeRepository.save(employee);
    return TOKEN_VALID;
  }

  @Override
  public void createVerificationTokenForEmployee(Employee employee, String token) {
    VerificationToken newtoken = new VerificationToken(token, employee);
    verificationTokenRepository.save(newtoken);
  }

  @Override
  public Employee getEmployee(String verificationToken) {
    VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
    if (token != null) {
      return token.getEmployee();
    }
    return null;
  }

  private boolean isEmailExist(String email) {
    return employeeRepository.findByEmail(email) != null;
  }

}
