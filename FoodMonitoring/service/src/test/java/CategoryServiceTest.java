import com.senla.courses.dto.CategoryDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.model.Category;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private final Category categoryOne = new Category(1, "овощи");
    private final Category categoryTwo = new Category(2, "фрукты");
    private final Category categoryThree = new Category(3, "бакалея");
    private final CategoryDto categoryDto = new CategoryDto(2, "овощи");

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> list = new ArrayList<>();
        list.add(categoryOne);
        list.add(categoryTwo);
        list.add(categoryThree);

        when(categoryRepository.findAll()).thenReturn(list);

        List<CategoryDto> empList = categoryService.getAllCategories();

        assertEquals(3, empList.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void getCategoryByIdTest() {
        when(categoryRepository.getById(1)).thenReturn(categoryOne);

        CategoryDto categoryDto = categoryService.getCategoryById(1);

        assertEquals(1, categoryDto.getId());
        assertEquals("овощи", categoryDto.getName());
    }

    @Test
    public void saveCategoryTest() {
        categoryService.saveCategory(categoryDto);

        verify(categoryRepository, times(1)).save(Mappers.getMapper(CategoryMapper.class).categoryDtoToCategory(categoryDto));
    }

    @Test
    public void deleteCategoryTest() {
        when(categoryRepository.getById(1)).thenReturn(categoryOne);

        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).delete(categoryOne);
    }

    @Test
    public void updateCategoryTest() {
        when(categoryRepository.getById(2)).thenReturn(categoryTwo);

        categoryService.updateCategory(categoryDto);

        verify(categoryRepository, times(1)).save(Mappers.getMapper(CategoryMapper.class).categoryDtoToCategory(categoryDto));
    }
}
