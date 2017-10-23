package com.ua.cabare.repositiries;

import com.ua.cabare.models.DishCategory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishCategoryRepository extends PagingAndSortingRepository<DishCategory, Long> {

  Optional<DishCategory> findById(Long id);

  DishCategory save(DishCategory dishCategory);

  @Query("SELECT dc FROM DishCategory dc")
  List<DishCategory> streamAllPaged(Pageable pageable);
}
