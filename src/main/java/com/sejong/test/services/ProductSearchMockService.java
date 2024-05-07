package com.sejong.test.services;

import com.sejong.test.domain.Product;
import com.sejong.test.domain.ProductSearchHistory;
import com.sejong.test.domain.User;
import com.sejong.test.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSearchMockService implements MockDataForeignService<ProductSearchHistory>{
    private final ProductSearchRepository productSearchRepository;
    private final ProductMockService productMockService;
    private final UserMockService userMockService;

    @Override
    public ProductSearchHistory create(Object... params){
        if(params[0] instanceof User user && params[1] instanceof Product product){
            return ProductSearchHistory.builder()
                    .user(user)
                    .product(product)
                    .build();
        }
        throw new IllegalArgumentException("params type error params[0] = user, params[1] = product");
    }

    @Override
    public void saveAll(List<ProductSearchHistory> entities) {
        productSearchRepository.saveAll(entities);
    }

    public void createMockProductSearchHistory(){
        List<User> users = userMockService.findAll();
        List<Product> products = productMockService.findAll();
        List<ProductSearchHistory> productSearchHistories = new ArrayList<>();
        for(int i=0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                User user = users.get(i);
                Product product = products.get(j);
                ProductSearchHistory productSearchHistory = create(user, product);
                productSearchHistories.add(productSearchHistory);
            }
        }
        saveAll(productSearchHistories);
    }
}
