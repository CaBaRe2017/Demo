package com.ua.cabare.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ua.cabare.domain.Money;
import com.ua.cabare.hibernate.custom.types.MoneyConverter;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dish extends EntityManager<Long, Dish> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private List<Calculation> calculations;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private Set<OrderItem> orderItems;

  @JsonProperty("dish_category")
  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "dish_category_id")
  private DishCategory dishCategory;

  @Column(name = "price")
  @Convert(converter = MoneyConverter.class)
  private Money price;

  @JsonProperty("start_day")
  @Column(name = "start_day")
  private Integer startDay;

  @JsonProperty("end_day")
  @Column(name = "end_day")
  private Integer endDay;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Calculation> getCalculations() {
    return calculations;
  }

  public void setCalculations(List<Calculation> calculations) {
    this.calculations = calculations;
  }

  public Set<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(Set<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public DishCategory getDishCategory() {
    return dishCategory;
  }

  public void setDishCategory(DishCategory dishCategory) {
    this.dishCategory = dishCategory;
  }

  public Money getPrice() {
    return price;
  }

  public void setPrice(Money price) {
    this.price = price;
  }

  public Integer getEndDay() {
    return endDay;
  }

  public void setEndDay(Integer endDay) {
    this.endDay = endDay;
  }

  public Integer getStartDay() {
    return startDay;
  }

  public void setStartDay(Integer startDay) {
    this.startDay = startDay;
  }
}


