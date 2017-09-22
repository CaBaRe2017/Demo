package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.DISH_NOT_FOUND;

public class DishNotFoundException extends DishException {

  public DishNotFoundException() {
    super(DISH_NOT_FOUND);
  }
}
