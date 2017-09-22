package com.ua.cabare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Money implements Serializable {


  transient public static final Money ZERO = new Money(0);
  @JsonIgnore
  private long value;
  @JsonProperty(defaultValue = "0")
  private String formatedValue;

  public long getValue() {
    return value;
  }

  public String getFormatedValue() {
    String s = String.valueOf(value);
    s = s.substring(0, s.length() - 2) + "." + s.substring(s.length() - 2, s.length());
    return s.length() == 3 ? "0" + s : s;
  }

  public void setFormatedValue(String formatedValue) {
    this.formatedValue = formatedValue;
    this.value = convertToLong(formatedValue);
  }

  public static Money valueOf(long value) {
    Money money = new Money();
    money.value = value;
    return money;
  }

  private Money() {
    this.value = 0;
  }

  public Money(Money money) {
    this.value = money.value;
    this.formatedValue = money.formatedValue;
  }

  public Money(long value) {
    this.value = value * 100;
  }

  public Money(String value) {
    this.value = convertToLong(value);
  }

  private long convertToLong(String value) {
    String str = value;
    int dotPosition = value.indexOf(".");
    if ((value.lastIndexOf(".") == dotPosition) && dotPosition != -1) {
      str = value.substring(0, dotPosition);
      int i = dotPosition + 1;
      int j = 0;
      while (i < value.length() && j++ < 2) {
        str += value.charAt(i++);
      }
    }
    return Long.parseLong(str);
  }

  public Money add(Money money) {
    return valueOf(money.value + value);
  }

  public Money subtract(Money money) {
    return valueOf(value - money.value);
  }

  public Money multiply(Money money) {
    return valueOf(value * money.value);
  }

  public Money multiply(long value) {
    return valueOf(this.value * value);
  }

  public Money multiply(double value) {
    return valueOf(((long) (this.value * value)));
  }

  public boolean isMoreThan(Money money) {
    return value > money.value;
  }

  public boolean isEqualTo(Money money) {
    return value == money.value;
  }

  public boolean isLessThan(Money money) {
    return value < money.value;
  }

  private boolean isZero() {
    return value == 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Money money = (Money) o;
    return value == money.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
