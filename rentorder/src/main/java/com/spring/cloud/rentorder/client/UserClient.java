package com.spring.cloud.rentorder.client;

import com.spring.cloud.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * ClassName: com.spring.cloud.rentorder.client.UserClient
 * Package: com.spring.cloud.rentorder.client
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午10:03
 * @Version 1.0
 */

//@FeignClient(name = "rentuser", url = "http://rentuser:8083") // docker-compose 要使用這個host
@FeignClient(name = "rentuser", url = "{user.service.url}")
public interface UserClient {
    /**
     * 透過 Feign 呼叫 User Service 拿到當前用戶信息
     * @param authHeader "Bearer <token>"
     * @return
     */
    @GetMapping("/api/users/profile")
    UserDto getProfile(@RequestHeader("Authorization") String authHeader);
}
