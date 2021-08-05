package com.senla.courses.service;

import com.senla.courses.api.IPriceComparison;
import com.senla.courses.api.IPriceDynamics;
import com.senla.courses.csv.ShopProductCsv;
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
import com.senla.courses.api.exception.ServiceException;
import com.senla.courses.api.service.IShopProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<ShopProductDto> getAllShopProducts(Pageable pageable) {
        try {
            Page<ShopProduct> shopProducts = shopProductRepository.findAll(pageable);
            return shopProducts.getContent().stream().map(mapper::shopProductToShopProductDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public ShopProductDto getShopProductById(Integer id) {
        try {
            ShopProduct shopProduct = shopProductRepository.getById(id);
            return mapper.shopProductToShopProductDto(shopProduct);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void saveShopProduct(ShopProductCsv shopProductCsv) {
        try {
            Shop shop = shopRepository.getById(shopProductCsv.getShop());
            Product product = productRepository.getById(shopProductCsv.getProduct());
            Double cost = shopProductCsv.getCost();
            LocalDate date = LocalDate.parse(shopProductCsv.getDate());
            ShopProduct shopProduct = new ShopProduct(shop, product, cost, date);
            shopProductRepository.save(shopProduct);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

    @Override
    public void deleteShopProduct(Integer id) {
        try {
            ShopProduct shopProduct = shopProductRepository.getById(id);
            shopProductRepository.delete(shopProduct);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw new ServiceException(DELETING_ERROR, e);
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
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }

    @Override
    public List<IPriceDynamics> getPriceDynamics(Integer id) {
        try {
            return shopProductRepository.avgProductCostByDate(id);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public List<IPriceComparison> getPriceComparison(LocalDate date, Integer shop1, Integer shop2) {
        try {
            return shopProductRepository.comparisonPricesByShops(date, shop1, shop2);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void saveFromShopProductCsv(List<ShopProductCsv> shopProductCsvList) {
        try {
            List<ShopProduct> shopProductList = new ArrayList<>();
        for(ShopProductCsv shopProductCsv : shopProductCsvList) {
            ShopProduct shopProduct = new ShopProduct();
            shopProduct.setShop(shopRepository.getById(shopProductCsv.getShop()));
            shopProduct.setProduct(productRepository.getById(shopProductCsv.getProduct()));
            shopProduct.setCost(shopProductCsv.getCost());
            shopProduct.setDate(LocalDate.parse(shopProductCsv.getDate()));
            shopProductList.add(shopProduct);
        }
        shopProductList.forEach(shopProductRepository::save);
        } catch (Exception e) {
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

}
