package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;
  @Column(name = "count")
  private int count;
  @Column(name = "total_price")
  private BigInteger totalPrice;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bill_id")
  private Bill bill;

  public OrderItem() {
  }

  public OrderItem(Dish dish, int count) {
    this.dish = dish;
    this.count = count;
    this.totalPrice = dish.getDishCost().getValue();
  }

  public Money getOrderItemPrice() {
    return new Money(totalPrice);
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

  public BigInteger getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigInteger totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }
}
