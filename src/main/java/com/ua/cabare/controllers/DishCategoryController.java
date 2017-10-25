package com.ua.cabare.controllers;

import static com.ua.cabare.domain.Response.DISH_CATEGORY;
import static com.ua.cabare.domain.Response.DISH_CATEGORY_LIST;
import static com.ua.cabare.domain.Response.STATUS;

import com.ua.cabare.domain.Response;
import com.ua.cabare.models.DishCategory;
import com.ua.cabare.services.DishCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dish_category")
public class DishCategoryController {

  @Autowired
  private DishCategoryService dishCategoryService;
  @Autowired
  private Response response;

  @RequestMapping(value = "/add", method = RequestMethod.PUT)
  public Response addNew(@RequestBody DishCategory dishCategory) {
    try {
      dishCategory = dishCategoryService.addNew(dishCategory);
      response.put(DISH_CATEGORY, dishCategory);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public Response update(@RequestBody DishCategory dishCategory) {
    try {
      DishCategory updated = dishCategoryService.update(dishCategory);
      response.put(DISH_CATEGORY, updated);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }

  @RequestMapping(value = "/get_dish_categories")
  public Response getCategories(Pageable pageable) {
    try {
      List<DishCategory> categories = dishCategoryService.getCategories(pageable);
      response.put(DISH_CATEGORY_LIST, categories);
    } catch (Exception ex) {
      response.put(STATUS, ex.getMessage());
    }
    return response;
  }
}
