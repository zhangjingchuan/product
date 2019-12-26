package com.mangmangbang.product.server.vo;

import lombok.Data;

/**
 *
 * http请求返回的数据格式封装
 * created by zhangjingchuan on 2019/12/23
 */
@Data
public class ResultFormat {
    public static Integer SUCCESS = 0;

    private Integer code;
    private String msg;
    private Object data;

    public ResultFormat(){

    }
    public ResultFormat(Object data){
        this.code = SUCCESS;
        this.msg = "请求成功";
        this.data = data;
    }

    public static ResultFormat success(Object data){
        return new ResultFormat(data);
    }
}
