package com.life.finmate.category.service;

import com.life.finmate.category.domain.MainCategory;
import com.life.finmate.category.dto.MainCategoryCreateRequestDto;
import com.life.finmate.category.dto.MainCategoryUpdateRequestDto;
import com.life.finmate.category.mapper.MainCategoryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainCategoryServiceTest {

    @Mock
    private MainCategoryMapper mainCategoryMapper;

    @Spy
    @InjectMocks
    private MainCategoryService mainCategoryService;

    @DisplayName("메인 카테고리 생성 성공 테스트")
    @Test
    void createMainCategory_success() {
        // given
        MainCategoryCreateRequestDto mainCategory = MainCategoryCreateRequestDto.builder()
                .categoryName("Category Name")
                .icon("Icon")
                .color("Color")
                .categoryType("income")
                .createdAt(LocalDateTime.now())
                .userId(1L)
                .build();
        doAnswer(invocation -> {
            MainCategory categoryArgument = invocation.getArgument(0);
            categoryArgument.setId(1L);
            return null;
        })
                .when(mainCategoryMapper).insertCategory(any(MainCategory.class));

        // when
        MainCategory foundCategory = mainCategoryService.createMainCategory(mainCategory);

        // then
        assertThat(foundCategory).isNotNull(); // 반환된 객체가 null이 아닌지 확인
        assertThat(foundCategory.getCategoryName()).isEqualTo(mainCategory.getCategoryName()); // 요청한 이름과 일치하는지 확인
        verify(this.mainCategoryMapper, times(1)).insertCategory(any(MainCategory.class));
    }

    @Test
    @DisplayName("유저 아이디 별 list를 조회하는 성공 테스트")
    void findAllMainCategoryByUserId_success() {
        // given
        Long userId = 1L;
        List<MainCategory> expectedCategories = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            expectedCategories.add(MainCategory.builder()
                    .id((long) i)
                    .categoryName("Category Name" + i)
                    .icon("Icon")
                    .color("Color")
                    .categoryType("income")
                    .createdAt(LocalDateTime.now())
                    .userId(userId)
                    .build());
        }

        when(mainCategoryMapper.selectMainCategoriesByUserId(userId)).thenReturn(expectedCategories);

        // when
        List<MainCategory> actualCategories = mainCategoryService.findAllMainCategoryByUserId(userId);

        //then
        assertThat(actualCategories).isNotNull();
        assertThat(actualCategories).hasSize(5);
        assertThat(actualCategories).isEqualTo(expectedCategories);
        verify(mainCategoryMapper, times(1)).selectMainCategoriesByUserId(userId);
    }

    @Test
    @DisplayName("단일 카테고리를 조회하는 성공 테스트")
    void findMainCategoryById_success() {
        // given
        Long userId = 1L;
        Long id = 1L;
        MainCategory expectedCategory = MainCategory.builder()
                .id(id)
                .categoryName("Category Name")
                .icon("Icon")
                .color("Color")
                .categoryType("income")
                .createdAt(LocalDateTime.now())
                .userId(userId)
                .build();
        when(mainCategoryMapper.selectMainCategoryById(id)).thenReturn(Optional.ofNullable(expectedCategory));

        //when
        MainCategory actualCategory = mainCategoryService.findMainCategoryById(id);


        //then
        assertThat(actualCategory).isNotNull();
        assertThat(actualCategory.getId()).isEqualTo(id);
        verify(this.mainCategoryMapper, times(1)).selectMainCategoryById(id);

    }

    @Test
    @DisplayName("카테고리 업데이트를 성공하는 테스트")
    void updateMainCategory_success() {
        // given
        Long userId = 1L;
        Long id = 1L;
        String updatedName = "testName";
        MainCategory initialCategory = MainCategory.builder()
                .id(id)
                .categoryName("Category Name")
                .icon("Icon")
                .color("Color")
                .categoryType("income")
                .createdAt(LocalDateTime.now())
                .userId(userId)
                .build();
        MainCategoryUpdateRequestDto updateRequestDto = MainCategoryUpdateRequestDto.builder()
                .categoryName(updatedName)
                .icon("Icon")
                .color("Color")
                .categoryType("income")
                .build();

        when(mainCategoryMapper.selectMainCategoryById(id)).thenReturn(Optional.of(initialCategory));
        doAnswer(invocation -> {
            MainCategory categoryToUpdate = invocation.getArgument(0);
            categoryToUpdate.update(
                    updateRequestDto.getCategoryName(),
                    updateRequestDto.getCategoryType(),
                    updateRequestDto.getIcon(),
                    updateRequestDto.getColor()
            );
            return null;
        }).when(mainCategoryMapper).updateCategoryById(any(MainCategory.class));

        //when

        MainCategory actualCategory = mainCategoryService.updateMainCategory(id, updateRequestDto);

        //then
        assertThat(actualCategory).isNotNull();
        assertThat(actualCategory.getId()).isEqualTo(id);
        assertThat(actualCategory.getCategoryName()).isEqualTo(updatedName);
        verify(this.mainCategoryMapper, times(1)).selectMainCategoryById(id);
        verify(this.mainCategoryMapper, times(1)).updateCategoryById(any(MainCategory.class));
    }

    @DisplayName("카테고리를 삭제하는 성공 테스트")
    @Test
    void deleteMainCategoryById() {
        // given
        Long id = 1L;
        MainCategory initialCategory = MainCategory.builder()
                .id(id)
                .categoryName("Category Name")
                .icon("Icon")
                .color("Color")
                .categoryType("income")
                .createdAt(LocalDateTime.now())
                .userId(1L)
                .build();

        doNothing().when(mainCategoryMapper).deleteCategoryById(id);

        // when
        mainCategoryService.deleteMainCategoryById(id);

        // then
        verify(this.mainCategoryMapper, times(1)).deleteCategoryById(id);
        assertThat(this.mainCategoryMapper.selectMainCategoryById(id)).isEmpty();

    }
}