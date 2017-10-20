package com.ua.cabare.security.dto;

import java.util.ArrayList;
import java.util.List;

public class ActiveEmployees {

  private List<String> employees;

  public ActiveEmployees() {
    employees = new ArrayList<>();
  }

  public List<String> getEmployees() {
    return employees;
  }

  public void setEmployees(List<String> employees) {
    this.employees = employees;
  }
}
