package com.ua.cabare.repositiries;

import com.ua.cabare.models.Discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

  Discount save(Discount discount);

  Optional<Discount> findByDiscountCard(String discountCard);

  Optional<Discount> findById(Long id);
}
