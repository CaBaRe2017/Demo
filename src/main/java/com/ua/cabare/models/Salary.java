package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salaries")
public class Salary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "total_salary")
  private Money totalSalary;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Money getTotalSalary() {
    return totalSalary;
  }

  public void setTotalSalary(Money totalSalary) {
    this.totalSalary = totalSalary;
  }
}
