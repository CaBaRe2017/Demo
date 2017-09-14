package com.ua.cabare;

import com.ua.cabare.models.Dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MainController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping("/add")
  public String addNewDish(@RequestParam String name, @RequestParam Double price) {

    Dish dish = new Dish();
    dish.setName(name);
    userRepository.save(dish);
    return "Complete";
  }

  @RequestMapping("/all")
  public Iterable<Dish> getAllDishes() {
    return userRepository.findAll();
  }

  @RequestMapping("/get")
  public Dish getOneDish(Long id) {
    return userRepository.findOne(id);
  }

  //@GetMapping(path = "/menu")
  //public @ResponseBody Iterable<Dish> getAllByColumn(String name) {
  //return userRepository.findAll(name);

}