package com.spring.cloud.common.handler;

import com.spring.cloud.common.dto.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName: com.spring.cloud.common.handler.GlobalExceptionHandler
 * Package: com.spring.cloud.common.handler
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 下午1:58
 * @Version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕捉所有未處理的 Exception，統一回傳 500
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 的HttpStatus
    public JsonResponse<?> handleGenericException(Exception ex) {
        // 可以 log.error(ex) 做後端追蹤
        ex.printStackTrace();
        return JsonResponse.fail("500", "Internal Server Error: " + ex.getMessage());
    }

}
