package com.senla.courses.service;

import com.senla.courses.dto.ShopDto;
import com.senla.courses.mapper.ShopMapper;
import com.senla.courses.model.Shop;
import com.senla.courses.repository.ShopRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.courses.api.exception.ServiceException;
import com.senla.courses.api.service.IShopService;
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
public class ShopService extends ConstantUtil implements IShopService {

    private final ShopRepository shopRepository;
    private final ShopMapper mapper = Mappers.getMapper(ShopMapper.class);

    @Override
    public List<ShopDto> getAllShops(Pageable pageable) {
        try {
            Page<Shop> shops = shopRepository.findAll(pageable);
            return shops.getContent().stream().map(mapper::shopToShopDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public ShopDto getShopById(Integer id) {
        try {
            Shop shop = shopRepository.getById(id);
            return mapper.shopToShopDto(shop);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void saveShop(ShopDto shopDto) {
        try {
            Shop shop = mapper.shopDtoToShop(shopDto);
            shopRepository.save(shop);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

    @Override
    public void deleteShop(Integer id) {
        try {
            Shop shop = shopRepository.getById(id);
            shopRepository.delete(shop);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw new ServiceException(DELETING_ERROR, e);
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
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }
}
