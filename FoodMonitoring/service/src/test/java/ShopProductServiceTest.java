import com.senla.courses.dto.ProductDto;
import com.senla.courses.dto.ShopDto;
import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.mapper.ShopProductMapper;
import com.senla.courses.model.Category;
import com.senla.courses.model.Product;
import com.senla.courses.model.Shop;
import com.senla.courses.model.ShopProduct;
import com.senla.courses.repository.ProductRepository;
import com.senla.courses.repository.ShopProductRepository;
import com.senla.courses.repository.ShopRepository;
import com.senla.courses.service.ShopProductService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShopProductServiceTest {

    private final Shop shopOne = new Shop(1, "Евроопт", "Минск, ул.Советская, 55");
    private final Shop shopTwo = new Shop(2, "Щедрый", "Минск, б-р Шевченко, 41");
    private final Shop shopThree = new Shop(3, "Санта", "Минск, п-т Победителей, 1");
    private final ShopDto shopDto = Mappers.getMapper(ShopMapper.class).shopToShopDto(shopOne);

    private final Category categoryOne = new Category(1, "овощи");
    private final Category categoryTwo = new Category(2, "фрукты");

    private final Product productOne = new Product(1, "огурец",categoryOne);
    private final Product productTwo = new Product(2, "яблоко", categoryTwo);
    private final Product productThree = new Product(3, "апельсин", categoryTwo);
    private final ProductDto productDto = Mappers.getMapper(ProductMapper.class).productToProductDto(productOne);

    private final ShopProduct shopProductOne = new ShopProduct(1, shopOne, productOne, 1.7, LocalDate.of(2021, 7, 10));
    private final ShopProduct shopProductTwo = new ShopProduct(2, shopTwo, productTwo, 2.4, LocalDate.of(2021, 7, 10));
    private final ShopProduct shopProductThree = new ShopProduct(3, shopThree, productThree, 1.9, LocalDate.of(2021, 7, 10));
    private final ShopProduct shopProductFour = new ShopProduct(shopOne, productOne, 1.7, LocalDate.of(2021, 7, 10));
    private final ShopProductDto shopProductDto = new ShopProductDto(2, shopDto, productDto, 1.7, LocalDate.of(2021, 7, 10));

    @Mock
    private ShopRepository shopRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ShopProductRepository shopProductRepository;
    @InjectMocks
    private ShopProductService shopProductService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllShopProductsTest() {
        List<ShopProduct> list = new ArrayList<>();
        list.add(shopProductOne);
        list.add(shopProductTwo);
        list.add(shopProductThree);
        Page<ShopProduct> page = new PageImpl<>(list);

        when(shopProductRepository.findAll(PageRequest.of(0, 3))).thenReturn(page);

        List<ShopProductDto> empList = shopProductService.getAllShopProducts(PageRequest.of(0, 3));

        assertEquals(3, empList.size());
        verify(shopProductRepository, times(1)).findAll(PageRequest.of(0, 3));
    }

    @Test
    public void getShopProductByIdTest() {
        when(shopProductRepository.getById(1)).thenReturn(shopProductOne);

        ShopProductDto shopProductDto = shopProductService.getShopProductById(1);

        assertEquals(1, shopProductDto.getId());
        assertEquals(shopOne, Mappers.getMapper(ShopMapper.class).shopDtoToShop(shopProductDto.getShop()));
        assertEquals(productOne, Mappers.getMapper(ProductMapper.class).productDtoToProduct(shopProductDto.getProduct()));
        assertEquals(1.7, shopProductDto.getCost());
        assertEquals(LocalDate.of(2021, 7, 10), shopProductDto.getDate());
    }

    @Test
    public void saveShopProductTest() {
        when(shopRepository.getById(1)).thenReturn(shopOne);
        when(productRepository.getById(1)).thenReturn(productOne);

        shopProductService.saveShopProduct(1, 1, LocalDate.of(2021, 7, 10), 1.7);

        verify(shopProductRepository, times(1)).save(shopProductFour);
    }

    @Test
    public void deleteShopProductTest() {
        when(shopProductRepository.getById(1)).thenReturn(shopProductOne);

        shopProductService.deleteShopProduct(1);

        verify(shopProductRepository, times(1)).delete(shopProductOne);
    }

    @Test
    public void updateShopProductTest() {
        when(shopProductRepository.getById(2)).thenReturn(shopProductTwo);

        shopProductService.updateShopProduct(shopProductDto);

        verify(shopProductRepository, times(1)).save(Mappers.getMapper(ShopProductMapper.class)
                .shopProductDtoToShopProduct(shopProductDto));
    }


}
