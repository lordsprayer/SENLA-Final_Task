package com.senla.courses.service;

import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.mapper.ShopProductMapper;
import com.senla.courses.model.Product;
import com.senla.courses.model.Shop;
import com.senla.courses.model.ShopProduct;
import com.senla.courses.repository.ProductRepository;
import com.senla.courses.repository.ShopProductRepository;
import com.senla.courses.repository.ShopRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.IShopProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ShopProductService extends ConstantUtil implements IShopProductService {

    private final ShopProductRepository shopProductRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final ShopProductMapper mapper = Mappers.getMapper(ShopProductMapper.class);

    @Override
    public List<ShopProductDto> getAllShopProducts() {
        try {
            List<ShopProduct> shopProducts = shopProductRepository.findAll();
            return mapper.shopProductListToShoProductDtoList(shopProducts);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public ShopProductDto getShopProductById(Integer id) {
        try {
            ShopProduct shopProduct = shopProductRepository.getById(id);
            return mapper.shopProductToShopProductDto(shopProduct);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void saveShopProduct(Integer shopId, Integer productId, LocalDate date, Double cost) {
        try {
            Shop shop = shopRepository.getById(shopId);
            Product product = productRepository.getById(productId);
            ShopProduct shopProduct = new ShopProduct(shop, product, cost, date);
            shopProductRepository.save(shopProduct);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteShopProduct(Integer id) {
        try {
            ShopProduct shopProduct = shopProductRepository.getById(id);
            shopProductRepository.delete(shopProduct);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void updateShopProduct(ShopProductDto shopProductDto) {
        try {
            ShopProduct shopProduct = shopProductRepository.getById(shopProductDto.getId());
            shopProduct.setShop(Mappers.getMapper(ShopMapper.class).shopDtoToShop(shopProductDto.getShop()));
            shopProduct.setProduct(Mappers.getMapper(ProductMapper.class).productDtoToProduct(shopProductDto.getProduct()));
            shopProduct.setCost(shopProductDto.getCost());
            shopProduct.setDate(shopProductDto.getDate());
            shopProductRepository.save(shopProduct);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }
}