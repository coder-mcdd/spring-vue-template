package jzxy.mcdd.backend.utils;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
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
public record RestBean<T> (long id, int code, T data, String message) {
    public static <T> RestBean<T> success(T data){
        return new RestBean<>(requestId(), HttpStatus.OK.value(), data, "请求成功");
    }

    public static <T> RestBean<T> success(){
        return success(null);
    }

    public static <T> RestBean<T> forbidden(String message){
        return failure(HttpStatus.FORBIDDEN.value(), message);
    }

    public static <T> RestBean<T> unauthorized(String message){
        return failure(HttpStatus.UNAUTHORIZED.value(), message);
    }

    public static <T> RestBean<T> failure(int code, String message){
        return new RestBean<>(requestId(), code, null, message);
    }

    public static <T> RestBean<T> noPermission() {
        return new RestBean<>(requestId(), HttpStatus.UNAUTHORIZED.value(), null, "权限不足，拒绝访问");
    }

    /**
     * 快速将当前实体转换为 JSON 字符串格式
     * @return JSON 字符串
     */
    public String asJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }

    /**
     * 获取当前请求 ID 方便快速定位错误
     * @return ID
     */
    private static long requestId(){
        String requestId = Optional.ofNullable(MDC.get("reqId")).orElse("0");
        return Long.parseLong(requestId);
    }
}
