package com.ua.cabare.models;

import java.math.BigInteger;
import java.time.LocalDate;

public class Discount {

  private long id;
  private String discountCard;
  private String firstName;
  private String lastName;
  private boolean male;
  private LocalDate birthday;
  private LocalDate emitted;
  private BigInteger totalPaid;
  private int discountSize;
  private boolean activated;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isActivated() {
    return activated;
  }

  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  public String getDiscountCard() {
    return discountCard;
  }

  public void setDiscountCard(String discountCard) {
    this.discountCard = discountCard;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isMale() {
    return male;
  }

  public void setMale(boolean male) {
    this.male = male;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public LocalDate getEmitted() {
    return emitted;
  }

  public void setEmitted(LocalDate emitted) {
    this.emitted = emitted;
  }

  public BigInteger getTotalPaid() {
    return totalPaid;
  }

  public void setTotalPaid(BigInteger totalPaid) {
    this.totalPaid = totalPaid;
  }

  public int getDiscountSize() {
    return discountSize;
  }

  public void setDiscountSize(int discountSize) {
    this.discountSize = discountSize;
  }
}
