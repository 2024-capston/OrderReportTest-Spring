package com.sejong.test.services;

import com.sejong.test.domain.User;
import com.sejong.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class UserMockServiceTest {
    @Autowired
    private UserMockService userMockService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }
    
    @Test
    void testCreateMany() { 
        int length = 10000;
        List<User> users = new ArrayList<>(length);;
        for(int i=0; i<length; i++) {
            User user = userMockService.create();
            users.add(user);
        }
        userMockService.saveAll(users);
    }
}