package com.ua.cabare.models;

import java.util.List;

public class Bill {

  private long id;
  private Waiter waiter;
  private List<Dish> dishes;
  private int tableCount;
  private SaleType saleType;
  private Discount discount;
  private PayType payType;
  private PayStatus payStatus;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public List<Dish> getDishes() {
    return dishes;
  }

  public void setDishes(List<Dish> dishes) {
    this.dishes = dishes;
  }

  public SaleType getSaleType() {
    return saleType;
  }

  public void setSaleType(SaleType saleType) {
    this.saleType = saleType;
  }

  public Waiter getWaiter() {
    return waiter;
  }

  public void setWaiter(Waiter waiter) {
    this.waiter = waiter;
  }

  public int getTableCount() {
    return tableCount;
  }

  public void setTableCount(int tableCount) {
    this.tableCount = tableCount;
  }

  public Discount getDiscount() {
    return discount;
  }

  public void setDiscount(Discount discount) {
    this.discount = discount;
  }

  public PayType getPayType() {
    return payType;
  }

  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  public PayStatus getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(PayStatus payStatus) {
    this.payStatus = payStatus;
  }
}
