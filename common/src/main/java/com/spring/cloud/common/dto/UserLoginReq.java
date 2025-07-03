package com.spring.cloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: com.spring.cloud.common.dto.UserLoginReq
 * Package: com.spring.cloud.common.dto
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/7/2 下午1:11
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReq {
    private String email;
    private String password;
}
