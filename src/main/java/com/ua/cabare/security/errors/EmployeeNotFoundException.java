package com.ua.cabare.security.errors;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(String message) {
    super(message);
  }
}
