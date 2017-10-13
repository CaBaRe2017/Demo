package com.ua.cabare.services;

import com.ua.cabare.domain.Money;
import com.ua.cabare.exceptions.DiscountCardNotFoundException;
import com.ua.cabare.models.Discount;
import com.ua.cabare.repositories.DiscountRepository;

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

  public Discount getById(Long id) throws DiscountCardNotFoundException {
    return discountRepository.findById(id).orElseThrow(() -> new DiscountCardNotFoundException());
  }

  public Discount getDiscountCard(String discountCard) throws DiscountCardNotFoundException {
    return findDiscountCard(discountCard);
  }

  public void changeDiscountSize(String discountCard, int newDiscountSize)
      throws DiscountCardNotFoundException {
    Discount discount = findDiscountCard(discountCard);
    discount.setDiscountSize(newDiscountSize);
    discountRepository.save(discount);
  }

  public int getDiscountSize(String discountCard) throws DiscountCardNotFoundException {
    Discount discount = findDiscountCard(discountCard);
    return discount.getDiscountSize();
  }

  public boolean blockDiscountCard(String discountCard) throws DiscountCardNotFoundException {
    Discount discount = findDiscountCard(discountCard);
    discount.setActivated(false);
    discountRepository.save(discount);
    return true;
  }

  public void addPayment(String discountCard, Money payment)
      throws DiscountCardNotFoundException {
    Discount discount = findDiscountCard(discountCard);
    Money totalPaid = discount.getTotalPaid().add(payment);
    discount.setTotalPaid(totalPaid);
    discountRepository.save(discount);
  }

  private Discount findDiscountCard(String discountCard) throws DiscountCardNotFoundException {
    return discountRepository.findByDiscountCard(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
  }
}
