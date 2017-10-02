package com.ua.cabare.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale_type")
public class SaleType extends EntityManager<Long, SaleType> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sale_type_title")
  private String saleTypeTitle;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getSaleTypeTitle() {
    return saleTypeTitle;
  }

  public void setSaleTypeTitle(String saleTypeTitle) {
    this.saleTypeTitle = saleTypeTitle;
  }
}
