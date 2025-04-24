package com.spring.cloud.rentorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * ClassName: com.spring.cloud.rentorder.config.SecurityConfig
 * Package: com.spring.cloud.rentorder.config
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午9:32
 * @Version 1.0
 */

/**
 * SecurityConfig 提供 Spring Security 的核心配置：
 * - 關閉 CSRF
 * - /auth/** 放行，任何人可訪問(註冊登入)
 * - 其他路径都需要认证（由 JwtAuthFilter 设置的 Authentication 决定）
 * - 注册 JwtAuthFilter，放在 UsernamePasswordAuthenticationFilter 之前执行
 */
@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http
                // 1. REST API 無需 CSRF token
                .csrf(csrf -> csrf.disable())
                // 2. 路由授權規則
                .authorizeHttpRequests(auth -> auth
                        // 放行 Swagger 相關路徑
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html,").permitAll()
                        // 允許所有 OPTIONS 請求，避免預檢請求被阻擋 (放行Token)
                        // 瀏覽器會先發送一個 OPTIONS 預檢請求來確認 CORS 配置是否允許這樣的請求
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 其它所有請求需先通過認證
                        .anyRequest().authenticated()
                )
                // 3. 把 JwtAuthFilter 加入到過濾器，在用戶名/密碼過濾前執行
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}