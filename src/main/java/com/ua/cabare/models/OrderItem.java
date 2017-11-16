package com.ua.cabare.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.cabare.domain.Money;
import com.ua.cabare.hibernate.custom.types.MoneyConverter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
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
public class OrderItem extends EntityManager<Long, OrderItem> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @JsonProperty(value = "dish_name")
  @Column(name = "dish_name")
  private String dishName;

  @Column(name = "quantity")
  private int quantity;

  @JsonProperty(value = "total_price")
  @Column(name = "total_price")
  @Convert(converter = MoneyConverter.class)
  private Money totalPrice;

  @Column(name = "comments")
  private String comments;

  @JsonProperty(value = "order_time")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  @Column(name = "order_time")
  private LocalDateTime orderTime;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bill_id")
  private Bill bill;

  public OrderItem() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(LocalDateTime orderTime) {
    this.orderTime = orderTime;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Money getTotalPrice() {
    if (totalPrice == null) {
      totalPrice = dish.getPrice().multiply(quantity);
    }
    return totalPrice;
  }

  public void setTotalPrice(Money totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Bill getBill() {
    return bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public String getDishName() {
    return dishName;
  }

  public void setDishName(String dishName) {
    this.dishName = dishName;
  }
}
