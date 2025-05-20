package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GoodsUserController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    private Result getAllGoods(){
        List<Goods> goodsList = goodsService.getAllGoods();
        log.info("查看全部商品:{}",goodsList);
        return Result.success(goodsList);
    }

    @GetMapping("/goods/{category}")
    private Result getGoodsByCategory(@PathVariable Long category){
        List<Goods> goodsList = goodsService.getGoodsByCategory(category);
        log.info("查看该分类{}下的商品:{}",category,goodsList);
        return Result.success(goodsList);
    }

    @GetMapping("/goodsById/{id}")
    private Result getGoodsById(@PathVariable Long id){
        Goods goods = goodsService.getGoodsById(id);
        return Result.success(goods);
    }
}
