package com.ua.cabare.security.errors;

public class EmployeeAlreadyExistException extends RuntimeException {

  public EmployeeAlreadyExistException(String message) {
    super(message);
  }
}
