package com.ua.cabare.models;

import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.domain.PayType;
import com.ua.cabare.domain.SaleType;

import java.util.List;

public class Bill {

  private long id;
  private Stuff stuff;
  private List<OrderItem> orderItems;
  private int tableCount;
  private Discount discount;

  private SaleType saleType;
  private PayType payType;
  private PayStatus payStatus;

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public SaleType getSaleType() {
    return saleType;
  }

  public void setSaleType(SaleType saleType) {
    this.saleType = saleType;
  }

  public Stuff getStuff() {
    return stuff;
  }

  public void setStuff(Stuff stuff) {
    this.stuff = stuff;
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
