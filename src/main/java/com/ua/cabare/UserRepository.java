package com.ua.cabare;

import org.springframework.data.repository.CrudRepository;

import com.ua.cabare.Dish;

public interface UserRepository extends CrudRepository<Dish, Long> {

}