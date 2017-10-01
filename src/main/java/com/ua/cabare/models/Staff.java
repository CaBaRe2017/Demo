package com.ua.cabare.models;

import com.ua.cabare.domain.Position;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "staffs")
public class Staff {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "position")
  private Position position;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
  private Set<Bill> bills;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "staff_roles", joinColumns = {@JoinColumn(name = "staff_id")})
  private Set<Role> roles;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "department_id")
  private Department department;

  @Column(name = "start_day")
  private LocalDate startDay;

  @Column(name = "fired_day")
  private LocalDate firedDay;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "salary_id")
  private Salary salary;

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  public Staff() {
  }

  public Staff(Staff staff) {
    this.id = staff.getId();
    this.name = staff.getName();
    this.position = staff.getPosition();
    this.bills = staff.getBills();
    this.roles = staff.getRoles();
    this.department = staff.getDepartment();
    this.startDay = staff.getStartDay();
    this.firedDay = staff.getFiredDay();
    this.salary = staff.getSalary();
    this.login = staff.getLogin();
    this.password = staff.getPassword();
    this.email = staff.getEmail();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Set<Bill> getBills() {
    return bills;
  }

  public void setBills(Set<Bill> bills) {
    this.bills = bills;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public LocalDate getStartDay() {
    return startDay;
  }

  public void setStartDay(LocalDate startDay) {
    this.startDay = startDay;
  }

  public LocalDate getFiredDay() {
    return firedDay;
  }

  public void setFiredDay(LocalDate firedDay) {
    this.firedDay = firedDay;
  }

  public Salary getSalary() {
    return salary;
  }

  public void setSalary(Salary salary) {
    this.salary = salary;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
