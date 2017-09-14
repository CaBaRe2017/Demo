package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.util.List;

public class Dish {

  private Long id;
  private String name;
  private List<RecipeIngredient> recipeIngredients;

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


