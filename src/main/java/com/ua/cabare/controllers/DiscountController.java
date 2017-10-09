package com.ua.cabare.controllers;

import com.ua.cabare.domain.Money;
import com.ua.cabare.exceptions.DiscountCardNotFoundException;
import com.ua.cabare.exceptions.FormatException;
import com.ua.cabare.models.Discount;
import com.ua.cabare.services.DiscountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discountcard")
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @RequestMapping(value = "/emittcard", method = RequestMethod.PUT)
  public void emittCard(Discount discount) {
    discountService.emittDiscountCard(discount);
  }

  @RequestMapping(value = "/getcard", method = RequestMethod.GET)
  public Discount getCard(String discountCard) throws DiscountCardNotFoundException {
    return discountService.getDiscountCard(discountCard);
  }

  @RequestMapping(value = "/editsize", method = RequestMethod.POST)
  public void editCard(String discountCard, int newDiscountSize)
      throws DiscountCardNotFoundException {
    discountService.changeDiscountSize(discountCard, newDiscountSize);
  }

  @RequestMapping(value = "/getsize", method = RequestMethod.GET)
  public int getSize(String discountCard) throws DiscountCardNotFoundException {
    return discountService.getDiscountSize(discountCard);
  }

  @RequestMapping(value = "/block", method = RequestMethod.POST)
  public void blockCard(String discountCard) throws DiscountCardNotFoundException {
    discountService.blockDiscountCard(discountCard);
  }

  @RequestMapping("/addpayment")
  public void addPayment(String discountCard, Money payment)
      throws DiscountCardNotFoundException {
    discountService.addPayment(discountCard, payment);
  }
}
