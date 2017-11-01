package com.ua.cabare.security;

import com.ua.cabare.security.dto.ActiveEmployees;

import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import org.springframework.stereotype.Component;

@Component
public class LoggedEmployees implements HttpSessionBindingListener {

  private String employeeName;
  private ActiveEmployees activeEmployees;

  public LoggedEmployees() {
  }

  public LoggedEmployees(String employeeName, ActiveEmployees activeEmployees) {
    this.employeeName = employeeName;
    this.activeEmployees = activeEmployees;
  }

  @Override
  public void valueBound(HttpSessionBindingEvent event) {
    List<String> employees = activeEmployees.getEmployees();
    LoggedEmployees employee = (LoggedEmployees) event.getValue();
    if (!employees.contains(employee.getEmployeeName())) {
      employees.add(employee.getEmployeeName());
    }
  }

  @Override
  public void valueUnbound(HttpSessionBindingEvent event) {
    List<String> employees = activeEmployees.getEmployees();
    LoggedEmployees employee = (LoggedEmployees) event.getValue();
    if (employees.contains(employee.getEmployeeName())) {
      employees.remove(employee.getEmployeeName());
    }
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }
}
