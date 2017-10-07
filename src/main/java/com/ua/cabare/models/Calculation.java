package com.ua.cabare.models;

import com.ua.cabare.domain.Money;
import com.ua.cabare.hibernate.custom.types.MoneyConverter;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "calculation")
public class Calculation extends EntityManager<Long, Calculation> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "start_date", columnDefinition = "date")
  private LocalDate startDate;

  @Column(name = "end_date", columnDefinition = "date")
  private LocalDate endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private RawMaterial rawMaterial;

  @Column(name = "quantity_for_dish")
  private float quantityForDish;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @Column(name = "dish_out")
  private int dishOut;

  @Column(name = "dish_price")
  @Convert(converter = MoneyConverter.class)
  private Money dishPrice;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stock_id")
  private Stock stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "write_off_calculation_id")
  private WriteOffCalculation writeOffCalculation;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public float getQuantityForDish() {
    return quantityForDish;
  }

  public void setQuantityForDish(float quantityForDish) {
    this.quantityForDish = quantityForDish;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  public int getDishOut() {
    return dishOut;
  }

  public void setDishOut(int dishOut) {
    this.dishOut = dishOut;
  }

  public Money getDishPrice() {
    return rawMaterial.getRawPrice().multiply(quantityForDish);
  }

  public void setDishPrice(Money dishPrice) {
    this.dishPrice = dishPrice;
  }

  public Stock getStock() {
    return stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public WriteOffCalculation getWriteOffCalculation() {
    return writeOffCalculation;
  }

  public void setWriteOffCalculation(WriteOffCalculation writeOffCalculation) {
    this.writeOffCalculation = writeOffCalculation;
  }
}
