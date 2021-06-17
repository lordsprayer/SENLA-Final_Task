package com.senla.courses.service;

import com.senla.courses.dto.CategoryDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.model.Category;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService extends ConstantUtil implements ICategoryService {

    private static final Logger log = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return mapper.categoryListToCategoryDtoList(categories);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        try {
            Category category = categoryRepository.getById(id);
            return mapper.categoryToCategoryDto(category);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        try {
            Category category = mapper.categoryDtoToCategory(categoryDto);
            categoryRepository.save(category);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try {
            Category category = categoryRepository.getById(id);
            categoryRepository.delete(category);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.getById(categoryDto.getId());
            category.setName(categoryDto.getName());
            //todo узнать, нужен ли сэйв
            categoryRepository.save(category);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }
}
