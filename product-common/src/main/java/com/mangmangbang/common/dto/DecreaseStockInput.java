package com.mangmangbang.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseStockInput {
    /**
     * 商品的id
     */
    private String productId;
    /**
     * 商品的数量
     */
    private Integer productQuantity;
}
