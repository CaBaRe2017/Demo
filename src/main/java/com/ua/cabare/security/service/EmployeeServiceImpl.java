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
import com.ua.cabare.security.event.ConfirmEmailEvent;
import com.ua.cabare.security.model.VerificationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  private HttpServletRequest request;

  public EmployeeServiceImpl(HttpServletRequest request) {
    this.request = request;
  }

  @Transactional
  @Override
  public Employee registerNewEmployeeAccount(EmployeeDto employeeDto) {
    if (isEmailExist(employeeDto.getEmail())) {
      throw new EmployeeAlreadyExistException("There is an account with that email address: "
          + employeeDto.getEmail());
    }

    Employee employee = employeeRepository.save(getEmployee(employeeDto));
    eventPublisher
        .publishEvent(new ConfirmEmailEvent(employee, request.getLocale(), getAppUrl(request)));

    return employee;
  }

  private Employee getEmployee(EmployeeDto employeeDto) {
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
    return employee;
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

  private String getAppUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":"
        + request.getServerPort() + request.getContextPath();
  }

}
