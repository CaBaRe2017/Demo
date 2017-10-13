package com.ua.cabare.repositories;

import com.ua.cabare.models.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findById(Long id);

  Optional<Employee> findByLogin(String login);

  Employee findByEmail(String email);



}
