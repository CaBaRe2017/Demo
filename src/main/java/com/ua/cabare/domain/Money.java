package com.ua.cabare.domain;

import java.math.BigInteger;

public class Money {

  private BigInteger value;

  public Money(double value) {
    this.value = new BigInteger(String.valueOf((long) (value * 100)));
  }

  public Money(long value) {
    this.value = new BigInteger(String.valueOf(value));
  }

  public Money(BigInteger value) {
    this.value = value;
  }

  public Money add(Money money) {
    return new Money(value.add(money.value));
  }

  public Money subtract(Money money) {
    return new Money(value.subtract(money.value));
  }

  public Money multiply(long value) {
    return new Money(this.value.multiply(new Money(value).value));
  }

  public Money multiply(double value) {
    return new Money(this.value.multiply(new Money(value).value));
  }

  public BigInteger getValue() {
    return value;
  }
}
