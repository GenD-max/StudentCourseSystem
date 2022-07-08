package com.gendml.entity;

import lombok.Getter;

//该类直接定义好静态方法 在控制层直接调用方法
//因为是响应状态类 所以不需要使用@Entity映射到数据库表
@Getter
public class ResponseResult<T> {
    private final Integer code;//响应码
    private final T data;//响应数据
    private final String message;//响应信息

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResultCode.SUCCESS, ResultCode.SUCCESS_MESSAGE, data);
    }

    public static ResponseResult<Void> success(String message) {
        return new ResponseResult<>(ResultCode.SUCCESS, message, null);
    }

    public static ResponseResult<Void> success() {
        return new ResponseResult<>(ResultCode.SUCCESS, ResultCode.SUCCESS_MESSAGE, null);
    }

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(ResultCode.SUCCESS, message, data);
    }

    public static ResponseResult<Void> error(String message) {
        return new ResponseResult<>(ResultCode.ERROR, message, null);
    }

    public static <T> ResponseResult<T> error(T data) {
        return new ResponseResult<>(ResultCode.ERROR, ResultCode.ERROR_MESSAGE, data);
    }

    public static <T> ResponseResult<T> error(String message, T data) {
        return new ResponseResult<>(ResultCode.ERROR, message, data);
    }

    //Void为void父类
    public static <Void> ResponseResult<Void> error() {
        return new ResponseResult<>(ResultCode.ERROR, ResultCode.ERROR_MESSAGE, null);
    }

    //自定义状态码和状态信息
    private static final class ResultCode {
        //状态码
        private static final Integer SUCCESS = 200;//相应成功
        private static final Integer ERROR = 500;//响应失败
        //状态信息
        private static final String NO_CONTENT_MESSAGE = "";//无信息
        private static final String ERROR_MESSAGE = "服务器错误，请联系管理员";//错误信息
        private static final String SUCCESS_MESSAGE = "成功";//成功信息
    }

}
