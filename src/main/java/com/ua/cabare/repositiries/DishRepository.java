package com.ua.cabare.repositiries;

import com.ua.cabare.models.Dish;
import com.ua.cabare.models.DishCategory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends CrudRepository<Dish, Long> {

  Optional<Dish> findById(long id);

  Dish save(Dish dish);

  @Query("SELECT d FROM Dish d WHERE d.startDay < ?1 and d.endDay > ?1")
  List<Dish> streamAllPaged(int dayOfYear, Pageable pageable);

  List<Dish> getDishesByDishCategory(DishCategory category, Pageable pageable);
}
