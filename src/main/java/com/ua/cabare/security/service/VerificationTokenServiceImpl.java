package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;
import com.ua.cabare.repositories.EmployeeRepository;
import com.ua.cabare.repositories.VerificationTokenRepository;
import com.ua.cabare.security.model.VerificationToken;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private static final String TOKEN_INVALID = "invalidToken";
  private static final String TOKEN_EXPIRED = "expired";
  private static final String TOKEN_VALID = "valid";

  @Autowired
  private VerificationTokenRepository verificationTokenRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public String validateVerificationToken(String token) {
    VerificationToken tokenFromDb = verificationTokenRepository.findByToken(token);
    if (tokenFromDb == null) {
      return TOKEN_INVALID;
    }

    Employee employee = tokenFromDb.getEmployee();
    Period period = Period.between(tokenFromDb.getExpiryDate(), LocalDate.now());
    if (period.getDays() >= 1) {
      verificationTokenRepository.delete(tokenFromDb);
      return TOKEN_EXPIRED;
    }

    employee.setEnabled(true);
    employeeRepository.save(employee);
    return TOKEN_VALID;
  }

  @Override
  public void createVerificationTokenForEmployee(Employee employee, String token) {
    VerificationToken newToken = new VerificationToken(token, employee);
    verificationTokenRepository.save(newToken);
  }
}
