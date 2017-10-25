package com.ua.cabare.exceptions;

import static com.ua.cabare.domain.ResponseStatus.DISH_CATEGORY_NOT_SPECIFIED;

public class DishCategoryNotSpecifiedException extends RuntimeException {

  public DishCategoryNotSpecifiedException() {
    super(DISH_CATEGORY_NOT_SPECIFIED);
  }
}
