package com.ua.cabare.exceptions;


import static com.ua.cabare.domain.ResponseStatus.DISCOUNT_CARD_NOT_FOUND;

public class DiscountCardNotFoundException extends Exception {

  public DiscountCardNotFoundException() {
    super(DISCOUNT_CARD_NOT_FOUND);
  }
}
