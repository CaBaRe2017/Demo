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
@Table(name = "ingridient")
public class Ingridient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "quantity_stored")
  private float quantityStored;
  @Column(name = "price_per_unit")
  private BigInteger pricePerUnit;

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

  public Money getPricePerUnit() {
    return new Money(pricePerUnit);
  }

  public void setPricePerUnit(Money pricePerUnit) {
    this.pricePerUnit = pricePerUnit.getValue();
  }
}
