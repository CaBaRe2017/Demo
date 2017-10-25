package com.ua.cabare.services;

import static com.ua.cabare.domain.ResponseStatus.DISH_CATEGORY_NOT_SPECIFIED;
import static com.ua.cabare.domain.ResponseStatus.DISH_NOT_FOUND;
import static com.ua.cabare.domain.ResponseStatus.DISH_NOT_SPECIFIED;

import com.ua.cabare.domain.Utils;
import com.ua.cabare.models.Dish;
import com.ua.cabare.models.DishCategory;
import com.ua.cabare.repositiries.DishRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

  @Autowired
  private DishRepository dishRepository;
  @Autowired
  DishCategoryService dishCategoryService;

  public Dish findDish(Long dishId) {
    return dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException(DISH_NOT_FOUND));
  }

  public Dish addDish(Dish dish) {
    DishCategory dishCategory = dish.getDishCategory();
    if (dishCategory == null || dishCategory.getId() == null) {
      throw new RuntimeException(DISH_CATEGORY_NOT_SPECIFIED);
    }
    DishCategory category = dishCategoryService.findById(dishCategory.getId());
    dish.setId(null);
    dish.setDishCategory(category);
    return dishRepository.save(dish);
  }

  public Dish update(Dish dish) {
    Long id = dish.getId();
    if (id == null) {
      throw new RuntimeException(DISH_NOT_SPECIFIED);
    }
    Dish dishStored = findDish(id);
    Dish dishUpdated = Utils.updateState(dishStored, dish);
    return dishRepository.save(dishUpdated);
  }

  public List<Dish> getDishes(Pageable pageable) {
    return dishRepository.streamAllPaged(pageable);
  }
}
