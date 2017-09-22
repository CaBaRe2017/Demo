package com.ua.cabare.repositiries;

import com.ua.cabare.models.Dish;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish, Long> {

  Optional<Dish> findById(long id);
}
