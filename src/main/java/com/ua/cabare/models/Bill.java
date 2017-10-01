package com.ua.cabare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.PayStatus;
import com.ua.cabare.domain.PayType;
import com.ua.cabare.domain.SaleType;

import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bills")
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stuff_id")
  private Stuff stuff;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;
  @Column(name = "table_count")
  private int tableCount;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id", nullable = true)
  private Discount discount;

  @JsonProperty(defaultValue = "0")
  @Type(type = "com.ua.cabare.hibernate.custom.types.MoneyDescriptor")
  @Column(name = "money_paid")
  private Money paid;

  @Column(name = "sale_type", nullable = false)
  private SaleType saleType;
  @Column(name = "pay_type", nullable = false)
  private PayType payType;

  @JsonIgnore
  @Column(name = "pay_status", nullable = false)
  private PayStatus payStatus;
  @JsonIgnore
  @Column(name = "opened", nullable = false, columnDefinition = "BIT(1) NULL DEFAULT 1")
  private boolean opened;

  public Bill() {
    this.orderItems = new ArrayList<>();
    this.paid = Money.ZERO;
  }

  public Money getPaid() {
    return paid;
  }

  public void setPaid(Money paid) {
    this.paid = paid;
  }

  public boolean isOpened() {
    return opened;
  }

  public void setOpened(boolean opened) {
    this.opened = opened;
  }

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

  @Override
  public String toString() {
    return "Bill{"
        + "id=" + id
        + ", stuff=" + stuff
        + ", orderItems=" + orderItems
        + ", tableCount=" + tableCount
        + ", discount=" + discount
        + ", paid=" + paid
        + ", saleType=" + saleType
        + ", payType=" + payType
        + ", payStatus=" + payStatus
        + ", opened=" + opened
        + '}';
  }

  @JsonIgnore
  public Money getOrdersCost() {
    Money cost = Money.ZERO;
    for (OrderItem orderItem : this.getOrderItems()) {
      cost.add(orderItem.getTotalPrice());
    }
    return cost;
  }

  public void addPayment(Money payment) {
    this.paid = this.paid.add(payment);
  }
}
