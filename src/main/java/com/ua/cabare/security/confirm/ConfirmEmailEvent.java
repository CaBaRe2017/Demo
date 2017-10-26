package com.ua.cabare.security.confirm;

import com.ua.cabare.models.Employee;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

public class ConfirmEmailEvent extends ApplicationEvent{

  private String appUrl;
  private Locale locale;
  private Employee employee;

  /**
   * Create a new ApplicationEvent.
   *
   * @param employee the object on which the event initially occurred (never {@code null})
   */
  public ConfirmEmailEvent(Employee employee, Locale locale, String appUrl) {
    super(employee);
    this.appUrl = appUrl;
    this.employee = employee;
    this.locale = locale;
  }

  public String getAppUrl() {
    return appUrl;
  }

  public Locale getLocale() {
    return locale;
  }

  public Employee getEmployee() {
    return employee;
  }
}