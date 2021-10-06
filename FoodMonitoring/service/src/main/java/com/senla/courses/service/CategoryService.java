package com.senla.courses.service;

import com.senla.courses.dto.CategoryDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.model.Category;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.api.exception.ServiceException;
import com.senla.courses.api.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategoryService implements ICategoryService, ConstantUtil {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public List<CategoryDto> getAllCategories(Pageable pageable) {
        try {
            Page<Category> categories = categoryRepository.findAll(pageable);
            return categories.getContent().stream().map(mapper::categoryToCategoryDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        try {
            Category category = categoryRepository.getById(id);
            return mapper.categoryToCategoryDto(category);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        try {
            Category category = mapper.categoryDtoToCategory(categoryDto);
            categoryRepository.save(category);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

    @Override
    public void deleteCategory(Integer id) {
        try {
            Category category = categoryRepository.getById(id);
            categoryRepository.delete(category);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw new ServiceException(DELETING_ERROR, e);
        }
    }

    @Override
    public void updateCategory(Integer id, CategoryDto categoryDto) {
        try {
            Category category = categoryRepository.getById(id);
            category.setName(categoryDto.getName());
            categoryRepository.save(category);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }
}
