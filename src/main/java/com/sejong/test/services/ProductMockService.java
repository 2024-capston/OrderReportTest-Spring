package com.sejong.test.services;

import com.sejong.test.domain.Product;
import com.sejong.test.repository.ProductRepository;
import com.sejong.test.utils.RandomCreateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductMockService implements MockDataService<Product> {
    private final ProductRepository productRepository;
    private final Integer PRODUCT_COUNT = 1000;
    @Override
    public Product create() {
        return Product.builder()
                .productName(RandomCreateUtil.createRandomString())
                .price(BigDecimal.valueOf(RandomCreateUtil.createRandomNumber(1000000,1000)))
                .build();
    }

    @Override
    public void saveAll(List<Product> entities) {
        productRepository.saveAll(entities);
    }

    public void createMockProduct() {
        List<Product> products = new ArrayList<>();
        for(int i=0; i<PRODUCT_COUNT; i++) {
            Product product = create();
            products.add(product);
        }
        saveAll(products);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
