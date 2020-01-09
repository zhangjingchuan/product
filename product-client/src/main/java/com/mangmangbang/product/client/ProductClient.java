package com.mangmangbang.product.client;

import com.mangmangbang.common.dto.DecreaseStockInput;
import com.mangmangbang.common.dto.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCT")
public interface ProductClient {

    /**
     * 获取商品列表（给订单服务用）
     * @param productIdList
     * @return
     */
    @PostMapping("/product/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 删除购物车
     * @param decreaseStockInputList
     */
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
