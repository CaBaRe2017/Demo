package com.ua.cabare.services;

import static com.ua.cabare.domain.ResponseStatus.DISCOUNT_CARD_NOT_FOUND;

import com.ua.cabare.domain.Money;
import com.ua.cabare.models.Discount;
import com.ua.cabare.repositiries.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

  @Autowired
  private DiscountRepository discountRepository;

  public boolean emittDiscountCard(Discount discount) {
    discount.setActivated(true);
    discountRepository.save(discount);
    return true;
  }

  public Discount getById(Long id) {
    return discountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException(DISCOUNT_CARD_NOT_FOUND));
  }

  public Discount getDiscountCard(String discountCard) {
    return findDiscountCard(discountCard);
  }

  public void changeDiscountSize(String discountCard, int newDiscountSize) {
    Discount discount = findDiscountCard(discountCard);
    discount.setDiscountSize(newDiscountSize);
    discountRepository.save(discount);
  }

  public int getDiscountSize(String discountCard) {
    Discount discount = findDiscountCard(discountCard);
    return discount.getDiscountSize();
  }

  public boolean blockDiscountCard(String discountCard) {
    Discount discount = findDiscountCard(discountCard);
    discount.setActivated(false);
    discountRepository.save(discount);
    return true;
  }

  public void addPayment(String discountCard, Money payment) {
    Discount discount = findDiscountCard(discountCard);
    Money totalPaid = discount.getTotalPaid().add(payment);
    discount.setTotalPaid(totalPaid);
    discountRepository.save(discount);
  }

  private Discount findDiscountCard(String discountCard) {
    return discountRepository.findByDiscountCard(discountCard)
        .orElseThrow(() -> new RuntimeException(DISCOUNT_CARD_NOT_FOUND));
  }
}
