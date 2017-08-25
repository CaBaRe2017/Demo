package com.ua.cabare.repositiries;

import com.ua.cabare.models.Discount;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DiscountRepository {

  public Discount save(Discount discount) {
    throw new RuntimeException("Not implemented");
  }

  public Optional<Discount> findByDiscountName(String discountCard) {
    throw new RuntimeException("Not implemented");
  }
}
