package com.ua.cabare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ua.cabare.Dish;
import com.ua.cabare.UserRepository;

@Controller
@RequestMapping(path="/menu")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewDish(@RequestParam String name
            , @RequestParam Double price) {

        Dish d = new Dish();
        d.setName(name);
        d.setPrice(price);
        userRepository.save(d);
        return "Complete";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Dish> getAllDishes() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/get")
    public @ResponseBody
    Dish getOneDish(Long id) {
        return userRepository.findOne(id);
    }

    //@GetMapping(path = "/menu")
    //public @ResponseBody Iterable<Dish> getAllByColumn(String name) {
    //return userRepository.findAll(name);

}