package com.sejong.test.repository;

import com.sejong.test.domain.ProductSearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSearchRepository extends JpaRepository<ProductSearchHistory, Long> {
    List<ProductSearchHistory> findByUserId(Long userId);
}
