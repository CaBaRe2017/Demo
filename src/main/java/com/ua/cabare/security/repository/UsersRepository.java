package com.ua.cabare.security.repository;

import com.ua.cabare.models.Staff;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Staff, Long> {

  Optional<Staff> findByLogin(String login);
}
