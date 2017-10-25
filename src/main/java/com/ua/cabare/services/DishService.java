package com.ua.cabare.services;

import com.ua.cabare.domain.Utils;
import com.ua.cabare.exceptions.DishCategoryNotSpecifiedException;
import com.ua.cabare.exceptions.DishNotFoundException;
import com.ua.cabare.exceptions.DishNotSpecifiedException;
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

  public Dish findDish(Long dishId) throws DishNotFoundException {
    return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
  }

  public Dish addDish(Dish dish) {
    DishCategory dishCategory = dish.getDishCategory();
    if (dishCategory == null || dishCategory.getId() == null) {
      throw new DishCategoryNotSpecifiedException();
    }
    DishCategory category = dishCategoryService.findById(dishCategory.getId());
    dish.setId(null);
    dish.setDishCategory(category);
    return dishRepository.save(dish);
  }

  public Dish update(Dish dish) {
    Long id = dish.getId();
    if (id == null) {
      throw new DishNotSpecifiedException();
    }
    Dish dishStored = findDish(id);
    Dish dishUpdated = Utils.updateState(dishStored, dish);
    return dishRepository.save(dishUpdated);
  }

  public List<Dish> getDishes(Pageable pageable) {
    return dishRepository.streamAllPaged(pageable);
  }
}
