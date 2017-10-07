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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "write_off_positions")
public class WriteOffPosition extends EntityManager<Long, WriteOffPosition> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "write_off_reason_id")
  private WriteOffReason writeOffReason;

  @Column(name = "write_off_date", columnDefinition = "date")
  private LocalDate writeOffDate;

  @Column(name = "write_off_quantity")
  private float writeOffQuantity;

  @Column(name = "price_of_last_supply")
  @Convert(converter = MoneyConverter.class)
  private Money priceOfLastSupply;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "writeOffPosition")
  private Set<RawMaterial> rawMaterials;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public WriteOffReason getWriteOffReason() {
    return writeOffReason;
  }

  public void setWriteOffReason(WriteOffReason writeOffReason) {
    this.writeOffReason = writeOffReason;
  }

  public LocalDate getWriteOffDate() {
    return writeOffDate;
  }

  public void setWriteOffDate(LocalDate writeOffDate) {
    this.writeOffDate = writeOffDate;
  }

  public float getWriteOffQuantity() {
    return writeOffQuantity;
  }

  public void setWriteOffQuantity(float writeOffQuantity) {
    this.writeOffQuantity = writeOffQuantity;
  }

  public Money getPriceOfLastSupply() {
    return priceOfLastSupply;
  }

  public void setPriceOfLastSupply(Money priceOfLastSupply) {
    this.priceOfLastSupply = priceOfLastSupply;
  }

  public Set<RawMaterial> getRawMaterials() {
    return rawMaterials;
  }

  public void setRawMaterials(Set<RawMaterial> rawMaterials) {
    this.rawMaterials = rawMaterials;
  }
}
