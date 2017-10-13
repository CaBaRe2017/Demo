package com.ua.cabare.security.controllers;

import com.ua.cabare.security.GenericResponse;
import com.ua.cabare.security.dto.EmployeeDto;
import com.ua.cabare.security.service.EmployeeServiceImpl;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private EmployeeServiceImpl employeeService;

  @RequestMapping(value = "/employee/registration", method = RequestMethod.POST)
  public GenericResponse showRegistrationForm(@RequestBody @Valid EmployeeDto employeeDto) {
    log.debug("Registering employee account with: " + employeeDto);
    employeeService.registerNewEmployeeAccount(employeeDto);
    return new GenericResponse("success");
  }
}
