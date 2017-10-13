package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.EMPLOYEE_NOT_FOUND;

public class EmployeeNotFoundException extends EmployeeException {

  public EmployeeNotFoundException() {
    super(EMPLOYEE_NOT_FOUND);
  }
}
