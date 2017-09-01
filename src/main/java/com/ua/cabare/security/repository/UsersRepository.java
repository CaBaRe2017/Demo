package com.ua.cabare.security.repository;

import com.ua.cabare.security.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);
}
