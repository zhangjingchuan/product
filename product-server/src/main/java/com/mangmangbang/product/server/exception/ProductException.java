package com.mangmangbang.product.server.exception;

import com.mangmangbang.product.server.enums.ResultEnum;

/**
 * created by zhangjingchuan on 2019/12/25
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
