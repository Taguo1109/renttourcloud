package com.spring.cloud.common.dto;

/**
 * ClassName: com.spring.cloud.common.dto.OrderDto
 * Package: com.spring.cloud.common.dto
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午10:17
 * @Version 1.0
 */

/**
 * OrderDto 用於服務間傳遞訂單訊息
 */
public record OrderDto(
        String orderId,
        String productName,
        String username,
        String userEmail) {
}
