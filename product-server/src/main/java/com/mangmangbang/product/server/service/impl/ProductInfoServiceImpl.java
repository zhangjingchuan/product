package com.mangmangbang.product.server.service.impl;

import com.mangmangbang.common.dto.DecreaseStockInput;
import com.mangmangbang.common.dto.ProductInfoOutput;
import com.mangmangbang.product.server.enums.ProductStatusEnum;
import com.mangmangbang.product.server.enums.ResultEnum;
import com.mangmangbang.product.server.exception.ProductException;
import com.mangmangbang.product.server.pojo.ProductInfo;
import com.mangmangbang.product.server.repository.ProductInfoRepository;
import com.mangmangbang.product.server.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by zhangjingchuan on 2019/12/23
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfoOutput> findUpAll() {

        ProductInfo productInfo = ProductInfo.builder()
                .productStatus(ProductStatusEnum.UP.getCode())
                .build();
        Example<ProductInfo> example = Example.of(productInfo);
        List<ProductInfo> all = this.productInfoRepository.findAll(example);
        List<ProductInfoOutput> outPutList = all.stream().map(info -> {
            ProductInfoOutput outPut = ProductInfoOutput.builder().build();
            BeanUtils.copyProperties(info,outPut);
            return outPut;
        }).collect(Collectors.toList());
        return outPutList;
    }

    @Override
    public List<ProductInfoOutput> findByProductIdIn(List<String> productIdList) {
//        Specification<ProductInfo> specification = new Specification<ProductInfo>() {
//
//            @Override
//            public Predicate toPredicate(Root<ProductInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Predicate predicate = root.get("productId").as(String.class).in(productIdList);
//                return predicate;
//            }
//        };
//
//        return this.productInfoRepository.findAll(specification);
        List<ProductInfo> productInfoList = this.productInfoRepository.findByProductIdIn(productIdList);
        List<ProductInfoOutput> outPutList = productInfoList.stream().map(info -> {
            ProductInfoOutput outPut = ProductInfoOutput.builder().build();
            BeanUtils.copyProperties(info,outPut);
            return outPut;
        }).collect(Collectors.toList());
        return outPutList;
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for(DecreaseStockInput decreaseStockInput : decreaseStockInputList){
            //查询商品
            Optional<ProductInfo> productInfoOptional = this.productInfoRepository.findById(decreaseStockInput.getProductId());

            //判断商品是否村子啊
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //判断商品数量
            ProductInfo productInfo = productInfoOptional.get();
            int count = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();

            if(count<0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(count);
            this.productInfoRepository.save(productInfo);
        }
    }
}
