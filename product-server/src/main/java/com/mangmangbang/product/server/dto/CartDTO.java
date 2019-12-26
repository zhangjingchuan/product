package com.mangmangbang.product.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by zhangjingchuan on 2019/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    /**
     * 商品的id
     */
    private String productId;
    /**
     * 商品的数量
     */
    private Integer productQuantity;
}
