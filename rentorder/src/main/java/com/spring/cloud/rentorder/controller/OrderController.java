package com.spring.cloud.rentorder.controller;

import com.spring.cloud.common.dto.OrderDto;
import com.spring.cloud.rentorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: com.spring.cloud.rentorder.controller.OrderController
 * Package: com.spring.cloud.rentorder.controller
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 上午10:54
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String orderId, @RequestHeader("Authorization") String authHeader) {
        log.info("訂單呼叫用戶");
        OrderDto dto = orderService.getOrderWithUser(orderId, authHeader);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/test/{orderId}")
    public ResponseEntity<String> getOrderId(@PathVariable String orderId, @RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.ok(orderId);
    }


}
