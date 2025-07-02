package com.spring.cloud.rentauth.repository;

import com.spring.cloud.rentauth.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName: com.spring.cloud.rentauth.repository.UserRepository
 * Package: com.spring.cloud.rentauth.repository
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午12:52
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    public UsersEntity findByEmail(String email);
}
