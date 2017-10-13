package com.ua.cabare.models;

import com.ua.cabare.domain.Money;
import com.ua.cabare.hibernate.custom.types.MoneyConverter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "raw_materials")
public class RawMaterial extends EntityManager<Long, RawMaterial> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "raw_title")
  private String rawTitle;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "raw_category_id")
  private RawCategory rawCategory;

  @Column(name = "unit_of_measure")
  private String unitOfMeasure;

  @Column(name = "raw_quantity")
  private float rawQuantity;

  @Column(name = "raw_price")
  @Convert(converter = MoneyConverter.class)
  private Money rawPrice;

  @Column(name = "purchase_date", columnDefinition = "date")
  private LocalDate purchaseDate;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "raw_material_supplier",
      joinColumns = {@JoinColumn(name = "raw_material_id")},
      inverseJoinColumns = {@JoinColumn(name = "supplier_id")})
  private Set<Supplier> supplier;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "rawMaterial")
  private Set<Calculation> calculations;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "rawMaterial")
  private  Set<Stock> stocks;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stocktake_id")
  private Stocktake stocktake;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "write_off_position_id")
  private WriteOffPosition writeOffPosition;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getRawTitle() {
    return rawTitle;
  }

  public void setRawTitle(String rawTitle) {
    this.rawTitle = rawTitle;
  }

  public RawCategory getRawCategory() {
    return rawCategory;
  }

  public void setRawCategory(RawCategory rawCategory) {
    this.rawCategory = rawCategory;
  }

  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public float getRawQuantity() {
    return rawQuantity;
  }

  public void setRawQuantity(float rawQuantity) {
    this.rawQuantity = rawQuantity;
  }

  public Money getRawPrice() {
    return rawPrice;
  }

  public void setRawPrice(Money rawPrice) {
    this.rawPrice = rawPrice;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  public Set<Supplier> getSupplier() {
    return supplier;
  }

  public void setSupplier(Set<Supplier> supplier) {
    this.supplier = supplier;
  }

  public Set<Calculation> getCalculations() {
    return calculations;
  }

  public void setCalculations(Set<Calculation> calculations) {
    this.calculations = calculations;
  }

  public Set<Stock> getStocks() {
    return stocks;
  }

  public void setStocks(Set<Stock> stocks) {
    this.stocks = stocks;
  }

  public Stocktake getStocktake() {
    return stocktake;
  }

  public void setStocktake(Stocktake stocktake) {
    this.stocktake = stocktake;
  }

  public WriteOffPosition getWriteOffPosition() {
    return writeOffPosition;
  }

  public void setWriteOffPosition(WriteOffPosition writeOffPosition) {
    this.writeOffPosition = writeOffPosition;
  }
}
