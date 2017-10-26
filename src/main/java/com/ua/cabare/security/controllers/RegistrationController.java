package com.ua.cabare.security.controllers;

import com.ua.cabare.models.Employee;
import com.ua.cabare.security.GenericResponse;
import com.ua.cabare.security.confirm.ConfirmEmailEvent;
import com.ua.cabare.security.dto.EmployeeDto;
import com.ua.cabare.security.service.EmployeeServiceImpl;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController("/registration")
public class RegistrationController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private EmployeeServiceImpl employeeService;

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private MessageSource messageSource;

  @RequestMapping(value = "/employee", method = RequestMethod.POST)
  @ResponseBody
  public GenericResponse showRegistrationForm(@RequestBody @Valid EmployeeDto employeeDto,
      HttpServletRequest request) {
    log.debug("Registering employee account with: " + employeeDto);
    Employee employee = employeeService.registerNewEmployeeAccount(employeeDto);
    eventPublisher.publishEvent(new ConfirmEmailEvent(employee, request.getLocale(), getAppUrl(request)));
    return new GenericResponse("success");
  }

  @RequestMapping(value = "/confirm", method = RequestMethod.GET)
  @ResponseBody
  public GenericResponse registrationConfirm(@RequestParam String token,WebRequest request)
      throws UnsupportedEncodingException {
    String result = employeeService.validateVerificationToken(token);
    if (result.equals("valid")) {
      Employee employee = employeeService.getEmployee(token);
      if (employee != null){
        return new GenericResponse(messageSource.getMessage("message.accountVerified",
            null, request.getLocale()));
      }
    }
    return new GenericResponse(messageSource.getMessage("auth.message." + result,
        null, request.getLocale()));
  }



  private String getAppUrl(HttpServletRequest request) {
    return "http://" + request.getServerName() + ":"
        + request.getServerPort() + request.getContextPath();
  }
}