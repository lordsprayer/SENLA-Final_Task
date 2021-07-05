import com.senla.courses.dto.CategoryDto;
import com.senla.courses.dto.ShopDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.model.Category;
import com.senla.courses.model.Shop;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.repository.ShopRepository;
import com.senla.courses.service.CategoryService;
import com.senla.courses.service.ShopService;
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
public class ShopServiceTest {

    private final Shop shopOne = new Shop(1, "Евроопт", "Минск, ул.Советская, 55");
    private final Shop shopTwo = new Shop(2, "Щедрый", "Минск, б-р Шевченко, 41");
    private final Shop shopThree = new Shop(3, "Санта", "Минск, п-т Победителей, 1");
    private final ShopDto shopDto = new ShopDto(2, "Евроопт", "Минск, ул.Советская, 55");

    @Mock
    private ShopRepository shopRepository;
    @InjectMocks
    private ShopService shopService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllShopsTest() {
        List<Shop> list = new ArrayList<>();
        list.add(shopOne);
        list.add(shopTwo);
        list.add(shopThree);

        when(shopRepository.findAll()).thenReturn(list);

        List<ShopDto> empList = shopService.getAllShops();

        assertEquals(3, empList.size());
        verify(shopRepository, times(1)).findAll();
    }

    @Test
    public void getShopByIdTest() {
        when(shopRepository.getById(1)).thenReturn(shopOne);

        ShopDto shopDto = shopService.getShopById(1);

        assertEquals(1, shopDto.getId());
        assertEquals("Евроопт", shopDto.getName());
        assertEquals("Минск, ул.Советская, 55", shopDto.getAddress());
    }

    @Test
    public void saveShopTest() {
        shopService.saveShop(shopDto);

        verify(shopRepository, times(1)).save(Mappers.getMapper(ShopMapper.class).shopDtoToShop(shopDto));
    }

    @Test
    public void deleteShopTest() {
        when(shopRepository.getById(1)).thenReturn(shopOne);

        shopService.deleteShop(1);

        verify(shopRepository, times(1)).delete(shopOne);
    }

    @Test
    public void updateShopTest() {
        when(shopRepository.getById(2)).thenReturn(shopTwo);

        shopService.updateShop(shopDto);

        verify(shopRepository, times(1)).save(Mappers.getMapper(ShopMapper.class).shopDtoToShop(shopDto));
    }
}
