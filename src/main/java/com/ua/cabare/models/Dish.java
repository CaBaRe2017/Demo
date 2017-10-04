package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private List<Calculation> calculations;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private Set<OrderItem> orderItems;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_category_id")
  private DishCategory dishCategory;

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

  public Money getDishCost() {
    Money cost = new Money(0);
    calculations
        .stream()
        .map(calculation -> calculation.getDishPrice())
        .forEach(c -> cost.add(c));
    return cost;
  }
}


