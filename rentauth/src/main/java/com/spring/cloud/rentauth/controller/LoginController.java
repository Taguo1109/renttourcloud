package com.spring.cloud.rentauth.controller;

import com.spring.cloud.common.dto.AuthRequest;
import com.spring.cloud.common.dto.AuthResponse;
import com.spring.cloud.common.dto.UserLoginReq;
import com.spring.cloud.common.util.JwtUtil;
import com.spring.cloud.rentauth.entity.UsersEntity;
import com.spring.cloud.rentauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: com.spring.cloud.rentauth.controller.LoginController
 * Package: com.spring.cloud.rentauth.controller
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/23 下午4:53
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    // 硬寫一組 Demo 帳密
    private static final Map<String, String> DEMO_USERS = Map.of("demo", "1234");

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("loginEmail")
    public ResponseEntity<UsersEntity> loginByEmail(@RequestBody UserLoginReq userLoginReq) {
        userService.login(userLoginReq.getEmail(), userLoginReq.getPassword());
        return ResponseEntity.ok(new UsersEntity());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        String pwd = DEMO_USERS.get(req.username());
        if (pwd != null && pwd.equals(req.password())) {
            String token = JwtUtil.generateToken(req.username());
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
