package com.ua.cabare.services;

import com.ua.cabare.exceptions.DishNotFoundException;
import com.ua.cabare.models.Dish;
import com.ua.cabare.repositiries.DishRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishService {

  @Autowired
  private DishRepository dishRepository;

  public Dish findDish(Long dishId) throws DishNotFoundException {
    return dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException());
  }
}
