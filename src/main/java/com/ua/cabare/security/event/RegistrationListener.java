package com.ua.cabare.security.event;

import com.ua.cabare.models.Employee;
import com.ua.cabare.security.service.VerificationTokenServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener {

  @Autowired
  private VerificationTokenServiceImpl verificationTokenService;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private JavaMailSender mailSender;

  @EventListener
  private void handleConfirmRegistration(ConfirmEmailEvent event) {
    Employee employee = event.getEmployee();
    String token = UUID.randomUUID().toString();
    verificationTokenService.createVerificationTokenForEmployee(employee, token);

    SimpleMailMessage email = createEmailMessage(event, employee, token);
    mailSender.send(email);
  }

  private SimpleMailMessage createEmailMessage(ConfirmEmailEvent emailEvent, Employee employee,
      String token) {
    String emailAddress = employee.getEmail();
    String subject = "Registration confirmation";
    String confirmationUrl = emailEvent.getAppUrl() + "/registration/event?token=" + token;
    String message = messageSource.getMessage("message.emailText", null, emailEvent.getLocale());
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(emailAddress);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setText(message + "\r\n" + confirmationUrl);
    simpleMailMessage.setFrom("spring.mail.username");
    return simpleMailMessage;
  }
}
