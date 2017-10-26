package com.ua.cabare.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("localhost");
    mailSender.setPort(2525);
    mailSender.setProtocol("smtps");
    mailSender.setUsername("${EMAIL_USERNAME}");
    mailSender.setPassword("${EMAIL_PASSWORD}");
    return mailSender;
  }
}
