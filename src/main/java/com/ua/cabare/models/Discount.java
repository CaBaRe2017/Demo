package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "discounts")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "discount_card")
  private String discountCard;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "male")
  private boolean male;
  @Column(name = "birthday")
  private LocalDate birthday;
  @Column(name = "emitted")
  private LocalDate emitted;
  @Column(name = "total_paid")
  private Money totalPaid;
  @Column(name = "discount_size")
  private int discountSize;
  @Column(name = "activated")
  private boolean activated;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
  private Set<Bill> bills;

  public Money getTotalPaid() {
    return totalPaid;
  }

  public void setTotalPaid(Money totalPaid) {
    this.totalPaid = totalPaid;
  }

  public Set<Bill> getBills() {
    return bills;
  }

  public void setBills(Set<Bill> bills) {
    this.bills = bills;
  }

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

  public int getDiscountSize() {
    return discountSize;
  }

  public void setDiscountSize(int discountSize) {
    this.discountSize = discountSize;
  }
}
