package com.ua.cabare.models;

import java.time.LocalDate;
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
@Table(name = "stocktake")
public class Stocktake extends EntityManager<Long, Stocktake> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "stocktake_date", columnDefinition = "date")
  private LocalDate stocktake_Date;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "stocktake")
  private Set<RawMaterial> rawMaterials;

  @Column(name = "actual_quantity")
  private float actualQuantity;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getStocktake_Date() {
    return stocktake_Date;
  }

  public void setStocktake_Date(LocalDate stocktake_Date) {
    this.stocktake_Date = stocktake_Date;
  }

  public Set<RawMaterial> getRawMaterials() {
    return rawMaterials;
  }

  public void setRawMaterials(Set<RawMaterial> rawMaterials) {
    this.rawMaterials = rawMaterials;
  }

  public float getActualQuantity() {
    return actualQuantity;
  }

  public void setActualQuantity(float actualQuantity) {
    this.actualQuantity = actualQuantity;
  }
}
