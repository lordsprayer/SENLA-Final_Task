package com.senla.courses.service;

import com.senla.courses.dto.ProductDto;
import com.senla.courses.mapper.CategoryMapper;
import com.senla.courses.mapper.ProductMapper;
import com.senla.courses.model.Category;
import com.senla.courses.model.Product;
import com.senla.courses.repository.CategoryRepository;
import com.senla.courses.repository.ProductRepository;
import com.senla.courses.util.ConstantUtil;
import com.senla.couses.api.exception.ServiceException;
import com.senla.couses.api.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductService extends ConstantUtil implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductDto getProductById(Integer id) {
        try {
            Product product = productRepository.getById(id);
            return mapper.productToProductDto(product);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public void saveProduct(String name, Integer categoryId) {
        try {
            Category category = categoryRepository.getById(categoryId);
            Product product = new Product(name, category);
            productRepository.save(product);
        } catch (Exception e){
            log.log(Level.WARN, SAVING_ERROR);
            throw new ServiceException(SAVING_ERROR, e);
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        try {
            Product product = productRepository.getById(id);
            productRepository.delete(product);
        }  catch (Exception e){
            log.log(Level.WARN, DELETING_ERROR);
            throw new ServiceException(DELETING_ERROR, e);
        }
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        try {
            Product product = productRepository.getById(productDto.getId());
            product.setName(productDto.getName());
            product.setCategory(Mappers.getMapper(CategoryMapper.class).categoryDtoToCategory(productDto.getCategory()));
            productRepository.save(product);
        } catch (Exception e){
            log.log(Level.WARN, UPDATING_ERROR);
            throw new ServiceException(UPDATING_ERROR, e);
        }
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        try {
            List<Product> products = productRepository.findByCategory_name(category);
            return mapper.productListToProductDtoList(products);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }

    @Override
    public List<ProductDto> getSortProductsBy(Sort sort) {
        try {
            List<Product> products = productRepository.findAll(sort);
            return mapper.productListToProductDtoList(products);
        } catch (Exception e) {
            log.log(Level.WARN, SEARCH_ERROR);
            throw new ServiceException(SEARCH_ERROR, e);
        }
    }
}
