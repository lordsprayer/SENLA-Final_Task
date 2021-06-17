package com.senla.courses.service;

import com.senla.courses.dto.ProductDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.model.Product;
import com.senla.courses.repository.ProductRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.service.IProductService;
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
public class ProductService extends ConstantUtil implements IProductService {

    private static final Logger log = LogManager.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            return mapper.productListToProductDtoList(products);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public ProductDto getProductById(Integer id) {
        try {
            Product product = productRepository.getById(id);
            return mapper.productToProductDto(product);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw e;
        }
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        try {
            Product product = mapper.productDtoToProduct(productDto);
            productRepository.save(product);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw e;
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            Product product = productRepository.getById(id);
            productRepository.delete(product);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw e;
        }
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        try {
            Product product = productRepository.getById(productDto.getId());
            product.setName(productDto.getName());
            product.setCategory(Mappers.getMapper(CategoryMapper.class).categoryDtoToCategory(productDto.getCategoryDto()));
            //todo узнать, нужен ли сэйв
            productRepository.save(product);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw e;
        }
    }
}
