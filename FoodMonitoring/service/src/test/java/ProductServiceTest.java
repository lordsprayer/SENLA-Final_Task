import com.senla.courses.dto.ProductDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.model.Category;
import com.senla.courses.model.Product;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.repository.ProductRepository;
import com.senla.courses.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private final Category categoryOne = new Category(1, "овощи");
    private final Category categoryTwo = new Category(2, "фрукты");
    private final Product productOne = new Product(1, "огурец",categoryOne);
    private final Product productTwo = new Product(2, "яблоко", categoryTwo);
    private final Product productThree = new Product(3, "апельсин", categoryTwo);
    private final Product productFour = new Product("огурец", categoryOne);
    private final ProductDto productDto = new ProductDto(2, "апельсин",
            Mappers.getMapper(CategoryMapper.class).categoryToCategoryDto(categoryTwo));


    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProductByIdTest() {
        when(productRepository.getById(1)).thenReturn(productOne);

        ProductDto productDto = productService.getProductById(1);

        assertEquals(1, productDto.getId());
        assertEquals("огурец", productDto.getName());
        assertEquals(Mappers.getMapper(CategoryMapper.class).categoryToCategoryDto(categoryOne), productDto.getCategory());
    }

    @Test
    public void saveProductTest() {
        when(categoryRepository.findById(1)).thenReturn(java.util.Optional.of(categoryOne));

        productService.saveProduct("огурец", 1);

        verify(productRepository, times(1)).save(productFour);
    }

    @Test
    public void deleteProductTest() {
        when(productRepository.getById(1)).thenReturn(productOne);

        productService.deleteProduct(1);

        verify(productRepository, times(1)).delete(productOne);
    }

    @Test
    public void updateProductTest() {
        when(productRepository.getById(2)).thenReturn(productTwo);

        productService.updateProduct(2, productDto);

        verify(productRepository, times(1))
                .save(Mappers.getMapper(ProductMapper.class).productDtoToProduct(productDto));
    }

    @Test
    public void getProductsByCategoryTest() {
        List<Product> list = new ArrayList<>();
        list.add(productTwo);
        list.add(productThree);

        when(productRepository.findByCategory_name("фрукты", PageRequest.of(0, 2))).thenReturn(list);

        List<ProductDto> empList = productService.getProductsByCategory("фрукты", PageRequest.of(0, 2));

        assertEquals(2, empList.size());
        verify(productRepository, times(1)).findByCategory_name("фрукты", PageRequest.of(0, 2));
    }

    @Test
    public void getSortProductsByNameTest() {
        List<Product> list = new ArrayList<>();
        list.add(productThree);
        list.add(productOne);
        list.add(productTwo);
        Page<Product> page = new PageImpl<>(list);

        when(productRepository.findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name")))).thenReturn(page);

        List<ProductDto> empList = productService.getSortProductsBy(PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name")));

        assertEquals(3, empList.size());
        verify(productRepository, times(1)).findAll(PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "name")));



    }
}
