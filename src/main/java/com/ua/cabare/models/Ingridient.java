package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

public class Ingridient {

  private long id;
  private String name;
  private float quantityStored;
  private Money pricePerUnit;

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
    return pricePerUnit;
  }

  public void setPricePerUnit(Money pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }
}
