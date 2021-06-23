package com.senla.courses.service;

import com.senla.courses.dto.ShopDto;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.model.Shop;
import com.senla.courses.repository.ShopRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.IShopService;
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
public class ShopService extends ConstantUtil implements IShopService {

    private static final Logger log = LogManager.getLogger(ShopService.class);
    private final ShopRepository shopRepository;
    private final ShopMapper mapper = Mappers.getMapper(ShopMapper.class);

    @Override
    public List<ShopDto> getAllShops() {
        try {
            List<Shop> shops = shopRepository.findAll();
            return mapper.shopListToShopDtoList(shops);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public ShopDto getShopById(Integer id) {
        try {
            Shop shop = shopRepository.getById(id);
            return mapper.shopToShopDto(shop);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void saveShop(ShopDto shopDto) {
        try {
            Shop shop = mapper.shopDtoToShop(shopDto);
            shopRepository.save(shop);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteShop(Integer id) {
        try {
            Shop shop = shopRepository.getById(id);
            shopRepository.delete(shop);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void updateShop(ShopDto shopDto) {
        try {
            Shop shop = shopRepository.getById(shopDto.getId());
            shop.setName(shopDto.getName());
            shop.setAddress(shopDto.getAddress());
            shopRepository.save(shop);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }
}
