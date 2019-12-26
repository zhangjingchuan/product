package com.mangmangbang.product.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * created by zhangjingchuan on 2019/12/23
 *
 * 商品上下架状态
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架"),
    ;

    private Integer code;
    private String message;

}
