package com.ua.cabare;

import org.springframework.data.repository.CrudRepository;

import com.ua.cabare.models.Dish;

public interface UserRepository extends CrudRepository<Dish, Long> {

}