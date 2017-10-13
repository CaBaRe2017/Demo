package com.ua.cabare.repositories;

import com.ua.cabare.models.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

  Department findDepartmentById(Long id);
}
