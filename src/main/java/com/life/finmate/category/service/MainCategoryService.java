package com.life.finmate.category.service;

import com.life.finmate.category.domain.MainCategory;
import com.life.finmate.category.dto.MainCategoryCreateRequestDto;
import com.life.finmate.category.dto.MainCategoryUpdateRequestDto;
import com.life.finmate.category.mapper.MainCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainCategoryService {

    private final MainCategoryMapper mainCategoryMapper;

    // Create
    public MainCategory createMainCategory(MainCategoryCreateRequestDto mainCategoryCreateRequestDto) {
        MainCategory mainCategory = mainCategoryCreateRequestDto.toEntity();
        mainCategoryMapper.insertCategory(mainCategory);

        return mainCategory;
    }

    // Read-List
    public List<MainCategory> findAllMainCategoryByUserId(Long userId) {
        return mainCategoryMapper.selectMainCategoriesByUserId(userId);

    }

    // Read-One
    public MainCategory findMainCategoryById(Long id) {
        return mainCategoryMapper.selectMainCategoryById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND : " + id)
        );

    }

    // Update
    public MainCategory updateMainCategory(Long id, MainCategoryUpdateRequestDto updateRequestDto) {
        MainCategory categoryEntity = findMainCategoryById(id);
        categoryEntity.update(
                updateRequestDto.getCategoryName(),
                updateRequestDto.getCategoryType(),
                updateRequestDto.getIcon(),
                updateRequestDto.getColor()
        );
        mainCategoryMapper.updateCategoryById(categoryEntity);
        return categoryEntity;
    }

    // Delete
    public void deleteMainCategoryById(Long id) {
        mainCategoryMapper.deleteCategoryById(id);
    }
}
