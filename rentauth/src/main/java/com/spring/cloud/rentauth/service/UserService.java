package com.spring.cloud.rentauth.service;

/**
 * ClassName: com.spring.cloud.rentauth.service.UserService
 * Package: com.spring.cloud.rentauth.service.impl
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午12:48
 * @Version 1.0
 */
public interface UserService {
    boolean login(String username, String password);
}
