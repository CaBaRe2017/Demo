package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.DISCOUNT_CARD;
import static com.ua.cabare.domain.Response.DISCOUNT_SIZE;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.Money;
import com.ua.cabare.domain.Response;
import com.ua.cabare.models.Discount;
import com.ua.cabare.services.DiscountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discountcard")
public class DiscountController {

  @Autowired
  private DiscountService discountService;
  @Autowired
  private Response response;

  @RequestMapping(value = "/emittcard", method = RequestMethod.PUT)
  public Response emittCard(@RequestBody Discount discount) {
    try {
      discountService.emittDiscountCard(discount);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/getcard/{discount_card}", method = RequestMethod.GET)
  public Response getCard(@PathVariable(name = "discount_card") String discountCard) {
    try {
      Discount card = discountService.getDiscountCard(discountCard);
      response.put(DISCOUNT_CARD, card);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/editsize", method = RequestMethod.POST)
  public Response editCard(@RequestParam(name = "discount_card") String discountCard,
      @RequestParam(name = "discount_size") int newDiscountSize) {
    try {
      discountService.changeDiscountSize(discountCard, newDiscountSize);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/getsize/{discount_card}", method = RequestMethod.GET)
  public Response getSize(@PathVariable(name = "discount_card") String discountCard) {
    try {
      int discountSize = discountService.getDiscountSize(discountCard);
      response.put(DISCOUNT_SIZE, discountSize);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/block", method = RequestMethod.POST)
  public Response blockCard(@RequestParam(name = "discount_card") String discountCard) {
    try {
      discountService.blockDiscountCard(discountCard);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/addpayment", method = RequestMethod.POST)
  public Response addPayment(@RequestParam(name = "discount_card") String discountCard,
      @RequestParam(name = "add_payment") Money payment) {
    try {
      discountService.addPayment(discountCard, payment);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
