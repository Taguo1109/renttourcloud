package com.spring.cloud.rentauth.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: com.spring.cloud.rentauth.entity.BaseEntity
 * Package: com.spring.cloud.rentauth.entity
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午12:57
 * @Version 1.0
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
