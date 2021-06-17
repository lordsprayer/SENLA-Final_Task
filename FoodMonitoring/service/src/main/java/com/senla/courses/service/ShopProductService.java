package com.senla.courses.service;

import com.senla.courses.dto.ShopProductDto;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.mapper.ShopProductMapper;
import com.senla.courses.model.ShopProduct;
import com.senla.courses.repository.ShopProductRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.IShopProductService;
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
public class ShopProductService extends ConstantUtil implements IShopProductService {

    private static final Logger log = LogManager.getLogger(ShopProductService.class);
    private final ShopProductRepository shopProductRepository;
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
    public void saveShopProduct(ShopProductDto shopProductDto) {
        try {
            ShopProduct shopProduct = mapper.shopProductDtoToShopProduct(shopProductDto);
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
            shopProduct.setShop(Mappers.getMapper(ShopMapper.class).shopDtoToShop(shopProductDto.getShopDto()));
            shopProduct.setProduct(Mappers.getMapper(ProductMapper.class).productDtoToProduct(shopProductDto.getProductDto()));
            shopProduct.setCost(shopProductDto.getCost());
            shopProduct.setDate(shopProductDto.getDate());
            //todo узнать, нужен ли сэйв
            shopProductRepository.save(shopProduct);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }
}
