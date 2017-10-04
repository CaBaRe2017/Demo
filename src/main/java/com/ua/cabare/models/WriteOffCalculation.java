package com.ua.cabare.models;

import java.time.LocalDate;
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
@Table(name = "write_off_calculations")
public class WriteOffCalculation extends EntityManager<Long, WriteOffCalculation> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "write_off_reason_id")
  private WriteOffReason writeOffReason;

  @Column(name = "write_off_date", columnDefinition = "date")
  private LocalDate writeOffDate;

  @Column(name = "write_off_quantity")
  private double writeOffQuantity;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "writeOffCalculation")
  private Set<Calculation> calculations;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public double getWriteOffQuantity() {
    return writeOffQuantity;
  }

  public void setWriteOffQuantity(double writeOffQuantity) {
    this.writeOffQuantity = writeOffQuantity;
  }

  public Set<Calculation> getCalculations() {
    return calculations;
  }

  public void setCalculations(Set<Calculation> calculations) {
    this.calculations = calculations;
  }
}
