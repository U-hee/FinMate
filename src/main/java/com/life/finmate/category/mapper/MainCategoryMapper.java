package com.life.finmate.category.mapper;

import com.life.finmate.category.domain.MainCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MainCategoryMapper {

    // Create
    void insertCategory(MainCategory mainCategory);

    // Read
    Optional<MainCategory> selectMainCategoryById(Long id);
    List<MainCategory> selectMainCategoriesByUserId(Long id);

    // Update
    void updateCategoryById(MainCategory mainCategory);

    // Delete
    void deleteCategoryById(Long id);
}
