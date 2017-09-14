package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

public class RecipeIngredient {

  private long id;
  private Ingridient ingridient;
  private float quantityForDish;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Ingridient getIngridient() {
    return ingridient;
  }

  public void setIngridient(Ingridient ingridient) {
    this.ingridient = ingridient;
  }

  public float getQuantityForDish() {
    return quantityForDish;
  }

  public void setQuantityForDish(float quantityForDish) {
    this.quantityForDish = quantityForDish;
  }

  public Money getCost() {
    return ingridient.getPricePerUnit().multiply(quantityForDish);
  }
}
