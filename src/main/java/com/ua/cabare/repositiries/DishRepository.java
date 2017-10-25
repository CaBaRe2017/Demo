package com.ua.cabare.repositiries;

import com.ua.cabare.models.Dish;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish, Long> {

  Optional<Dish> findById(long id);

  Dish save(Dish dish);

  @Query("SELECT d FROM Dish d")
  List<Dish> streamAllPaged(Pageable pageable);
}
