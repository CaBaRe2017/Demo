package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.DISH_NOT_SPECIFIED;

public class DishNotSpecifiedException extends RuntimeException {

  public DishNotSpecifiedException() {
    super(DISH_NOT_SPECIFIED);
  }
}
