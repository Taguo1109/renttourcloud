package com.spring.cloud.rentorder.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cloud.common.dto.JsonResponse;
import com.spring.cloud.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: com.spring.cloud.rentorder.config.JwtAuthFilter
 * Package: com.spring.cloud.rentorder.config
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午9:32
 * @Version 1.0
 */

/**
 * JwtAuthFilter
 * 1. 從 header 提取 token
 * 2. 驗證 JWT
 * 3. 設置 Spring Security 的上下文
 * 4. 若失敗，回傳 401 + JSON 格式錯誤訊息
 */
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            // 驗證 token 是否有效
            if (JwtUtil.validateToken(token)) {
                // 從 token 解析 username，這邊也可以解析更多資訊
                String username = JwtUtil.getSubject(token);

                // 建立 Authentication 並設定到 SecurityContext
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(authToken);

                // 放行請求
                chain.doFilter(req, res);
                return;
            }
        }

        // 若 token 無效或不存在，返回 401 JSON 格式錯誤訊息
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.setContentType("application/json");
        JsonResponse<String> response = JsonResponse.unauthorized("Invalid or missing JWT token");
        String json = new ObjectMapper().writeValueAsString(response);
        res.getWriter().write(json);
    }
}