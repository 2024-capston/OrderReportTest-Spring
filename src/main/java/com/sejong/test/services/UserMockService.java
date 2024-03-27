package com.sejong.test.services;

import com.sejong.test.domain.User;
import com.sejong.test.repository.UserRepository;
import com.sejong.test.utils.RandomCreateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMockService implements MockDataService<User>{
    private final UserRepository userRepository;
    private final Integer USER_COUNT = 1000;
    @Override
    public User create() {
        return User.builder()
                .email(RandomCreateUtil.createRandomString())
                .username(RandomCreateUtil.createRandomString())
                .build();
    }

    @Override
    public void saveAll(List<User> entities) {
        userRepository.saveAll(entities);
    }

    public void createMockUser() {
        List<User> users = new ArrayList<>();
        for(int i=0; i<USER_COUNT; i++) {
            User user = create();
            users.add(user);
        }
        saveAll(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
