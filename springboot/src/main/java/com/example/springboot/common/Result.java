package com.example.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的接口返回结果
 * 包含了响应码(code)，响应信息(msg)和数据(data)三个属性
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private String code;
    private String msg;
    private Object data;

    public static <T> Result<T> success() {
        return new Result<T>(Constants.CODE_200, "", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(Constants.CODE_200, "", data);
    }

//    public static Result<>

    public static <T> Result<T> error(String code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public static <T> Result<T> error() {
        return new Result<T>(Constants.CODE_500, "系统错误", null);
    }
}
