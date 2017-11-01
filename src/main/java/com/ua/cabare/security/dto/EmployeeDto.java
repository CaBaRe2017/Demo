package com.ua.cabare.security.dto;

import com.ua.cabare.security.validation.MatchPassword;
import com.ua.cabare.security.validation.ValidEmail;
import com.ua.cabare.security.validation.ValidPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@MatchPassword
public class EmployeeDto {

  @NotNull
  @Size(min = 1)
  private String name;

  @NotNull
  private Integer position;

  @NotNull
  private Integer role;

  @NotNull
  private Integer department;

  @NotNull
  @Size(min = 6)
  private String startDay;

  //private LocalDate firedDay;
  //private Double salary;

  @NotNull
  @Size(min = 6)
  private String birthday;

  @NotNull
  @Size(min = 1)
  private String phone;

  @ValidEmail
  @Email
  @NotNull
  @Size(min = 1)
  private String email;

  @ValidPassword
  private String password;

  @NotNull
  private String matchingPassword;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public Integer getDepartment() {
    return department;
  }

  public void setDepartment(Integer department) {
    this.department = department;
  }

  public String getStartDay() {
    return startDay;
  }

  public void setStartDay(String startDay) {
    this.startDay = startDay;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }
}
