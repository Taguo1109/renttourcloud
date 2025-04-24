package com.spring.cloud.rentorder.service;

import com.spring.cloud.common.dto.OrderDto;
import com.spring.cloud.common.dto.UserDto;
import com.spring.cloud.rentorder.client.UserClient;
import org.springframework.stereotype.Service;

/**
 * ClassName: com.spring.cloud.rentorder.service.OrderService
 * Package: com.spring.cloud.rentorder.service
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午10:31
 * @Version 1.0
 */

@Service
public class OrderService {
    private final UserClient userClient;

    public OrderService(UserClient userClient) {
        this.userClient = userClient;
    }

    /**
     * 取得客戶訂單詳情
     * @param orderId
     * @param jwtToken
     * @return
     */
    public OrderDto getOrderWithUser(String orderId, String jwtToken) {
        // 1. 調用User Service
        UserDto user = userClient.getProfile(jwtToken);
        // 2. 返回OrderDto
        return new OrderDto(
                orderId,
                "Demo Product",
                user.username(),
                user.email()
        );
    }
}
