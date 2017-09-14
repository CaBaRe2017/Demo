package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.math.BigInteger;

import javax.persistence.Transient;

public class OrderItem {

  private
  long id;
  private Dish dish;
  private int count;
  private BigInteger totalPrice;
  @Transient
  private Money orderItemPrice;


  public OrderItem(Dish dish, int count) {
    this.dish = dish;
    this.count = count;
    this.totalPrice = orderItemPrice.getValue();
    this.orderItemPrice = dish.getDishCost();
  }

  public Money getOrderItemPrice() {
    return orderItemPrice;
  }

  public void setOrderItemPrice(Money orderItemPrice) {
    this.orderItemPrice = orderItemPrice;
    this.totalPrice = orderItemPrice.getValue();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
