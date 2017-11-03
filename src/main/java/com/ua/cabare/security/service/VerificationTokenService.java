package com.ua.cabare.security.service;

import com.ua.cabare.models.Employee;

public interface VerificationTokenService {

  String validateVerificationToken(String token);

  void createVerificationTokenForEmployee(Employee employee, String token);

}
