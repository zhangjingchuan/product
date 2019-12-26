package com.mangmangbang.product.server.service;

import com.mangmangbang.common.dto.DecreaseStockInput;
import com.mangmangbang.common.dto.ProductInfoOutput;

import java.util.List;

/**
 * created by zhangjingchuan on 2019/12/23
 */
public interface ProductInfoService {

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfoOutput> findUpAll();

    /**
     * 根据指定id获取商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findByProductIdIn(List<String> productIdList);

    /**
     * 扣除库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
