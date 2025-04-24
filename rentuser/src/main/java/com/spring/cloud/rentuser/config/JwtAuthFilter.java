package com.spring.cloud.rentuser.config;

import com.spring.cloud.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: com.spring.cloud.rentuser.config.JwtAuthFilter
 * Package: com.spring.cloud.rentuser.config
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午9:32
 * @Version 1.0
 */
public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        // 1. 讀取 Authorization header
        String h = req.getHeader("Authorization");
        if (h != null && h.startsWith("Bearer ")) {

            // 2. 獲取 token
            String token = h.substring(7);

            // 3. 驗證 token
            if (JwtUtil.validateToken(token)) {

                // 4. 解析用戶身份
                String user = JwtUtil.getSubject(token);

                // 5. 構造 Authentication 對象，放進 SecurityContext
                var auth = new UsernamePasswordAuthenticationToken(user, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);

                // 6. 通過，調用下一個 filter / controller
                chain.doFilter(req, res);
                return;
            }
        }
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}