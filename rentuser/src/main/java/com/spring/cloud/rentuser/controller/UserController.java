package com.spring.cloud.rentuser.controller;

import com.spring.cloud.common.dto.JsonResponse;
import com.spring.cloud.common.dto.UserDto;
import com.spring.cloud.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: com.spring.cloud.rentuser.controller.UserController
 * Package: com.spring.cloud.rentuser.controller
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午11:36
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/profile")
    public JsonResponse<UserDto> getProfile(@RequestHeader("Authorization") String authHeader) {
        log.info("呼叫用呼資訊");
        // 從 Authorization header 中提取 token
        String token = authHeader.substring(7); // "Bearer " 後面的部分

        // 驗證 token (這裡應該使用你的 JwtUtil)
        if (!JwtUtil.validateToken(token)) {
            return JsonResponse.unauthorized("token無效"); // 返回 401 如果 token 無效
        }

        // 從 token 中獲取使用者資訊
        String username = JwtUtil.getSubject(token);
        // 這裡應該從資料庫或其他來源獲取使用者的完整資訊
        // 為了簡化，我們直接創建一個 UserDto 物件
        UserDto userDto = new UserDto(username, username + "@example.com");

        return JsonResponse.ok(userDto);
    }
}