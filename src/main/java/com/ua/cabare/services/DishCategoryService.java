package com.ua.cabare.services;

import com.ua.cabare.domain.Utils;
import com.ua.cabare.exceptions.DishCategoryNotFoundException;
import com.ua.cabare.models.DishCategory;
import com.ua.cabare.repositiries.DishCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishCategoryService {

  @Autowired
  private DishCategoryRepository dishCategoryRepository;

  public DishCategory findById(Long id) {
    return dishCategoryRepository.findById(id)
        .orElseThrow(() -> new DishCategoryNotFoundException());
  }

  public DishCategory addNew(DishCategory dishCategory) {
    dishCategory.setId(null);
    return dishCategoryRepository.save(dishCategory);
  }

  public List<DishCategory> getCategories(Pageable pageable) {
    return dishCategoryRepository.streamAllPaged(pageable);
  }

  public DishCategory update(DishCategory dishCategory) {
    DishCategory dishCategoryStored = dishCategoryRepository.findById(dishCategory.getId())
        .orElseThrow(() -> new DishCategoryNotFoundException());
    DishCategory dishCategoryUpdated = Utils.updateState(dishCategoryStored, dishCategory);
    return dishCategoryRepository.save(dishCategoryUpdated);
  }
}
