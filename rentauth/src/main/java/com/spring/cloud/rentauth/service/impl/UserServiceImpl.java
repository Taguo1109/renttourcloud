package com.spring.cloud.rentauth.service.impl;

import com.spring.cloud.common.dto.UserLoginReq;
import com.spring.cloud.common.util.MapperUtils;
import com.spring.cloud.rentauth.entity.UsersEntity;
import com.spring.cloud.rentauth.repository.UserRepository;
import com.spring.cloud.rentauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: com.spring.cloud.rentauth.service.impl.UserServiceImpl
 * Package: com.spring.cloud.rentauth.service.impl
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午12:50
 * @Version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String email, String password) {
        UsersEntity byUsername = userRepository.findByEmail(email);
        log.info("username is {}", byUsername.getUsername());
        UserLoginReq map = MapperUtils.map(byUsername, UserLoginReq.class);
        log.info("map is {}", map.toString());
        return map.getPassword().equals(password) && map.getEmail().equals(email);
    }
}
