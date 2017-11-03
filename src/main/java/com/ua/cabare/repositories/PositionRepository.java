package com.ua.cabare.repositories;

import com.ua.cabare.models.Position;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

  Position getPositionById(Long id);
}
