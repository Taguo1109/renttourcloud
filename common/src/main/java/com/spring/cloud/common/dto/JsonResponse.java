package com.spring.cloud.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: com.spring.cloud.common.dto.JsonResponse
 * Package: com.spring.cloud.common.dto
 * Description:
 *
 * @Author 郭庭安
 * @Create 2025/4/24 下午1:33
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class JsonResponse<T> {
    private String status;
    private String message;
    private T data;


    public static <T> JsonResponse<T> ok(T data) {
        return new JsonResponse<>("200", "OK", data);
    }

    public static <T> JsonResponse<T> ok(String message, T data) {
        return new JsonResponse<>("200", message, data);
    }

    public static <T> JsonResponse<T> unauthorized(String message) {
        return new JsonResponse<>("401", message, null);
    }

    public static <T> JsonResponse<T> fail(String status, String message) {
        return new JsonResponse<>(status, message, null);
    }

}
