package jzxy.mcdd.backend.utils;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * RestBean
 *
 * @version 1.0.0
 * @author: mcdd
 * @date: 2024/8/30 22:18
 */
@AllArgsConstructor
@Data
public class RestBean<T> {
    private static final String DEFAULT_SUCCESS_MSG = "请求成功";
    private static final String DEFAULT_ERROR_MSG = "内部错误，请联系管理员 mcdd1024@gmail.com";
    private Long id;
    private Integer code;
    private T data;
    private String message;

    public static <T> RestBean<T> success() {
        return success(HttpStatus.OK.value(), DEFAULT_SUCCESS_MSG);
    }

    public static <T> RestBean<T> success(T data) {
        return new RestBean<>(requestId(), HttpStatus.OK.value(), data, DEFAULT_SUCCESS_MSG);
    }

    public static <T> RestBean<T> success(int code, String message) {
        return new RestBean<>(requestId(), HttpStatus.OK.value(), null, DEFAULT_SUCCESS_MSG);
    }

    public static <T> RestBean<T> failure() {
        return RestBean.failure(HttpStatus.BAD_REQUEST.value(), DEFAULT_ERROR_MSG);
    }

    public static <T> RestBean<T> failure(int code, String message) {
        return new RestBean<>(requestId(), code, null, message);
    }

    public static <T> RestBean<T> noPermission() {
        return new RestBean<>(requestId(), HttpStatus.UNAUTHORIZED.value(), null, "权限不足，拒绝访问");
    }

    public static <T> RestBean<T> forbidden(String message) {
        return failure(HttpStatus.FORBIDDEN.value(), message);
    }

    public static <T> RestBean<T> unauthorized(String message) {
        return failure(HttpStatus.UNAUTHORIZED.value(), message);
    }

    /**
     * 快速将当前实体转换为 JSON 字符串格式
     *
     * @return JSON 字符串
     */
    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

    /**
     * 获取当前请求 ID 方便快速定位错误
     *
     * @return ID
     */
    private static long requestId() {
        String requestId = Optional.ofNullable(MDC.get("reqId")).orElse("0");
        return Long.parseLong(requestId);
    }
}
