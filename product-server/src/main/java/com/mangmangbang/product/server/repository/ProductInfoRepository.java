package com.mangmangbang.product.server.repository;

import com.mangmangbang.product.server.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * created by zhangjingchuan on 2019/12/23
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>,JpaSpecificationExecutor<ProductInfo> {
    List<ProductInfo> findByProductIdIn(List<String> idList);
}
