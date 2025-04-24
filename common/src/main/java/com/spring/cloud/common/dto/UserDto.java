package com.spring.cloud.common.dto;

/**
 * ClassName: com.spring.cloud.common.dto.UserDto
 * Package: com.spring.cloud.common.dto
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午10:17
 * @Version 1.0
 */

/**
 * UserDto 用於服務間傳遞User訊息
 */
public record UserDto(String username, String email) {
}
