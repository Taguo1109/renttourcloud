package com.spring.cloud.rentauth;

import com.spring.cloud.common.dto.UserLoginReq;
import com.spring.cloud.common.util.MapperUtils;
import com.spring.cloud.rentauth.entity.UsersEntity;
import com.spring.cloud.rentauth.repository.UserRepository;
import com.spring.cloud.rentauth.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class RentauthApplicationTests {
    private Logger logger = LoggerFactory.getLogger(RentauthApplicationTests.class);


    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
    }

    @Test
    void login_shouldCallUserRepository_andMapToDTO() {
        // Arrange（安排／準備階段）
        String email = "test@example.com";
        String password = "password123";

        UsersEntity mockUser = new UsersEntity();
        mockUser.setUsername("阿莓");
        mockUser.setEmail(email);
        mockUser.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(mockUser);

        // 你如果用 MapStruct 或實際 mapper，要考慮 mock MapperUtils 或真實 mapping
        mockStatic(MapperUtils.class).when(() ->
                MapperUtils.map(any(UsersEntity.class), eq(UserLoginReq.class))
        ).thenReturn(new UserLoginReq(email, password));

        // Act（執行／行為階段）
        boolean result = userService.login(email, password);

        // Assert（斷言／驗證階段）
        verify(userRepository).findByEmail(email);
        // 還有assertTrue,assertEquals("test@example.com",result.getEmail)
        /**
         * 多個斷言一次測
         * assertAll("檢查使用者資訊",
         *     () -> assertEquals("阿莓", result.getUsername()),
         *     () -> assertEquals("berry@test.com", result.getEmail())
         * );
         */
        logger.info("result is {}", result);
    }
}
