package com.ua.cabare.services;

import com.ua.cabare.domain.Money;
import com.ua.cabare.exceptions.DiscountCardNotFoundException;
import com.ua.cabare.models.Discount;
import com.ua.cabare.repositiries.DiscountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DiscountService {

  private Pattern paymentPattern = Pattern.compile("^\\d+[\\.,]{1}\\d{2}$");

  @Autowired
  private DiscountRepository discountRepository;

  public boolean emittDiscountCard(Discount discount) {
    discount.setActivated(true);
    discountRepository.save(discount);
    return true;
  }

  public Discount getDiscountCard(String discountCard) throws DiscountCardNotFoundException {
    return discountRepository.findByDiscountName(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
  }

  public boolean changeDiscountSize(String discountCard, int newDiscountSize)
      throws DiscountCardNotFoundException {
    Discount discount = discountRepository.findByDiscountName(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
    discount.setDiscountSize(newDiscountSize);
    discountRepository.save(discount);
    return true;
  }

  public int getDiscountSize(String discountCard) throws DiscountCardNotFoundException {
    Discount discount = discountRepository.findByDiscountName(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
    return discount.getDiscountSize();
  }

  public boolean blockDiscountCard(String discountCard) throws DiscountCardNotFoundException {
    Discount discount = discountRepository.findByDiscountName(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
    discount.setActivated(false);
    discountRepository.save(discount);
    return true;
  }

  public boolean addPayment(String discountCard, Money payment)
      throws DiscountCardNotFoundException {
    Discount discount = discountRepository.findByDiscountName(discountCard)
        .orElseThrow(() -> new DiscountCardNotFoundException());
    Money totalPaid = discount.getTotalPaid().add(payment);
    discount.setTotalPaid(totalPaid);
    discountRepository.save(discount);
    return true;
  }

  private boolean isValidPayment(String payment) {
    return paymentPattern.matcher(payment).find();
  }
}
