package com.mangmangbang.product.server.service.impl;

import com.mangmangbang.product.server.pojo.ProductCategory;
import com.mangmangbang.product.server.repository.ProductCategoryRepository;
import com.mangmangbang.product.server.service.ProductCategoryService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * created by zhangjingchuan on 2019/12/23
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Resource
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {

        Specification<ProductCategory> specification = new Specification<ProductCategory>() {

            @Override
            public Predicate toPredicate(Root<ProductCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = root.get("categoryType").as(Integer.class).in(categoryTypeList);
                return predicate;
            }
        };

        return productCategoryRepository.findAll(specification);
    }
}
