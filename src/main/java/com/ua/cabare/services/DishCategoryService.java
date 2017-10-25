package com.ua.cabare.services;

import static com.ua.cabare.domain.ResponseStatus.DISH_CATEGORY_NOT_FOUND;
import static com.ua.cabare.domain.ResponseStatus.DISH_CATEGORY_NOT_SPECIFIED;

import com.ua.cabare.domain.Utils;
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
        .orElseThrow(() -> new RuntimeException(DISH_CATEGORY_NOT_FOUND));
  }

  public DishCategory addNew(DishCategory dishCategory) {
    dishCategory.setId(null);
    return dishCategoryRepository.save(dishCategory);
  }

  public List<DishCategory> getCategories(Pageable pageable) {
    return dishCategoryRepository.streamAllPaged(pageable);
  }

  public DishCategory update(DishCategory dishCategory) {
    Long id = dishCategory.getId();
    if (id == null) {
      throw new RuntimeException(DISH_CATEGORY_NOT_SPECIFIED);
    }
    DishCategory dishCategoryStored = findById(id);
    DishCategory dishCategoryUpdated = Utils.updateState(dishCategoryStored, dishCategory);
    return dishCategoryRepository.save(dishCategoryUpdated);
  }
}
