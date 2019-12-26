package com.mangmangbang.product.server.controller;

import com.mangmangbang.common.dto.DecreaseStockInput;
import com.mangmangbang.common.dto.ProductInfoOutput;
import com.mangmangbang.product.server.pojo.ProductCategory;
import com.mangmangbang.product.server.service.ProductCategoryService;
import com.mangmangbang.product.server.service.ProductInfoService;
import com.mangmangbang.product.server.vo.ProductInfoVo;
import com.mangmangbang.product.server.vo.ProductVo;
import com.mangmangbang.product.server.vo.ResultFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品
 * created by zhangjingchuan on 2019/12/23
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductInfoService productInfoService;
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultFormat list(){
        //查询所有在架的商品
        List<ProductInfoOutput> productInfoOutputList = this.productInfoService.findUpAll();
        //获取类目type列表
        List<Integer> categoryTypeList = productInfoOutputList.stream().map(p -> p.getCategoryType()).collect(Collectors.toList());
        //从数据库查询类目
        List<ProductCategory> categoryList = this.productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //构造数据
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory : categoryList){

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfoOutput productInfoOutput : productInfoOutputList){
                if(productInfoOutput.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = ProductInfoVo.builder().build();
                    BeanUtils.copyProperties(productInfoOutput, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            ProductVo vo = ProductVo.builder()
                    .categoryName(productCategory.getCategoryName())
                    .categoryType(productCategory.getCategoryType())
                    .productInfoVoList(productInfoVoList).build();

            productVoList.add(vo);
        }


        return ResultFormat.success(productVoList);
    }

    /**
     * 获取商品列表（给订单服务用）
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList){

        List<ProductInfoOutput> list = this.productInfoService.findByProductIdIn(productIdList);
        return list;
    }

    /**
     * 删除购物车
     * @param decreaseStockInputList
     */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
        this.productInfoService.decreaseStock(decreaseStockInputList);
    }
}
