package com.sejong.test.services;

import java.util.List;

public interface MockDataService<T> {
    /**
     * 저장할 entity 생성
     * @return T
     */
    T create();

    /**
     * entities save
     * @param entities
     */
    void saveAll(List<T> entities);
}
