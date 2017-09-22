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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dish {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "name")
  private String name;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private List<RecipeIngredient> recipeIngredients;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
  private Set<OrderItem> orderItems;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RecipeIngredient> getRecipeIngredients() {
    return recipeIngredients;
  }

  public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
    this.recipeIngredients = recipeIngredients;
  }

  public Money getDishCost() {
    Money cost = new Money(0);
    recipeIngredients
        .stream()
        .map(recipeIngredient -> recipeIngredient.getCost())
        .forEach(c -> cost.add(c));
    return cost;
  }
}


