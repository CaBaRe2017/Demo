package com.ua.cabare.models;

import com.ua.cabare.domain.Money;

import java.util.Set;

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
@Table(name = "recipe_ingredients")
public class RecipeIngredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ingridient_id")
  private Ingridient ingridient;
  @Column(name = "quantity_for_dish")
  private float quantityForDish;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;

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
    return ingridient.getPricePerUnitMoney().multiply(quantityForDish);
  }
}
