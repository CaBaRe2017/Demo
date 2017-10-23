package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.DISH_CATEGORY_NOT_FOUND;

public class DishCategoryNotFoundException extends RuntimeException {

  public DishCategoryNotFoundException() {
    super(DISH_CATEGORY_NOT_FOUND);
  }
}
