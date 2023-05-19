package com.eio.ggkt.result;


import lombok.Data;

import java.util.Random;

//统一返回结果
@Data
public class Result<T> {

    //状态码
    private Integer code;

    //返回状态信息
    private String message;

    //返回数据
    private T data;

    public Result(){}



    //成功的方法,有data数据
    public static <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(20000);
        result.setMessage("成功");
        return result;
    }


    //失败的方法，有data数据
    public static <T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(20001);
        result.setMessage("失败");
        return result;
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
