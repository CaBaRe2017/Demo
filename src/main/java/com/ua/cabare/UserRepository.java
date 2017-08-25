package com.ua.cabare;

import com.ua.cabare.models.Dish;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Dish, Long> {

}