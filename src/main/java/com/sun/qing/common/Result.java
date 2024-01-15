//package com.sun.qing.common;
//
//
//import lombok.Data;
//
///**
// * 全局统一响应的JSON格式处理类
// *
// */
//@Data
//public class Result<T> {
//    private Integer code;
//    private String message;
//    private T data;
//    public Result(){}
//
//    public Result(Integer code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }
//
//    public static <T> Result<T> ok(T data){
//        Result<T> result = new Result<>();
//        ResultCodeEnum success = ResultCodeEnum.SUCCESS;
//        result.code = success.getCode();
//        result.message = success.getMessage();
//        result.data = data;
//        return result;
//    }
//
//    public static <T>  Result<T> fail(T data){
//        Result<T> result = new Result<>();
//        ResultCodeEnum usernaemError = ResultCodeEnum.USERNAEM_ERROR;
//        result.code = usernaemError.getCode();
//        result.message = usernaemError.getMessage();
//        result.data = data;
//        return result;
//    }
//}


package com.sun.qing.common;


/**
 * 全局统一响应的JSON格式处理类
 *
 */
public class Result<T> {
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private T data;
    public Result(){}
    // 返回数据
    protected static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }
    public static <T> Result<T> build(T body, Integer code, String message) {
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }
    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}