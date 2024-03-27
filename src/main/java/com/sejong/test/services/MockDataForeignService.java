package com.sejong.test.services;

import java.util.List;

public interface MockDataForeignService<T> {
    /**
     * 저장할 entity 생성
     * @return T
     */
    T create(Object... params);

    /**
     * entities save
     * @param entities
     */
    void saveAll(List<T> entities);
}
