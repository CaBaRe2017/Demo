package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.DISH;
import static com.ua.cabare.domain.Response.DISH_LIST;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.Response;
import com.ua.cabare.models.Dish;
import com.ua.cabare.services.DishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

  @Autowired
  private Response response;
  @Autowired
  private DishService dishService;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response addDish(@RequestBody Dish dish) {
    try {
      Dish dishStored = dishService.addDish(dish);
      response.put(DISH, dishStored);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response update(@RequestBody Dish dish) {
    try {
      Dish updated = dishService.update(dish);
      response.put(DISH, updated);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/get_all")
  public Response getDishes(Pageable pageable) {
    try {
      List<Dish> dishes = dishService.getDishes(pageable);
      response.put(DISH_LIST, dishes);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
