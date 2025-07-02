package com.spring.cloud.rentauth.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * ClassName: com.spring.cloud.rentauth.entity.UsersEntity
 * Package: com.spring.cloud.rentauth.entity
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午12:55
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "users")
public class UsersEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String username;
    private String role;
}
