package com.mangmangbang.product.server.service;

import com.mangmangbang.product.server.pojo.ProductCategory;

import java.util.List;

/**
 * created by zhangjingchuan on 2019/12/23
 */
public interface ProductCategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
