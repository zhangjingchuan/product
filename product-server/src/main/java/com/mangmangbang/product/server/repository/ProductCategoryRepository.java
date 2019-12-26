package com.mangmangbang.product.server.repository;

import com.mangmangbang.product.server.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * created by zhangjingchuan on 2019/12/23
 */
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer>,JpaSpecificationExecutor<ProductCategory> {

}
