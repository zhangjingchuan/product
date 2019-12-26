package com.mangmangbang.product.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by zhangjingchuan on 2019/12/25
 */
@Getter
@AllArgsConstructor
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_STOCK_ERROR(2,"库存有误"),
    ;

    private Integer code;
    private String message;
}
