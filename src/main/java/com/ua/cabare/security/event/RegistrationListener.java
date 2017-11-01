package com.ua.cabare.security.event;

import com.ua.cabare.models.Employee;
import com.ua.cabare.security.service.EmployeeService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<ConfirmEmailEvent> {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public void onApplicationEvent(ConfirmEmailEvent event) {
    this.confirmRegistration(event);
  }

  private void confirmRegistration(ConfirmEmailEvent event) {
    Employee employee = event.getEmployee();
    String token = UUID.randomUUID().toString();
    employeeService.createVerificationTokenForEmployee(employee, token);

    SimpleMailMessage email = createEmailMessage(event, employee, token);
    mailSender.send(email);
  }

  private SimpleMailMessage createEmailMessage(ConfirmEmailEvent emailEvent, Employee employee,
      String token) {
    String emailAddress = employee.getEmail();
    String subject = "Registration confirmation";
    String confirmationUrl = emailEvent.getAppUrl() + "/registration/event?token=" + token;
    String message = messageSource.getMessage("message.regSuccess", null, emailEvent.getLocale());
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(emailAddress);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message + "\r\n" + confirmationUrl);
    simpleMailMessage.setFrom("spring.mail.username");
    return simpleMailMessage;
  }
}
