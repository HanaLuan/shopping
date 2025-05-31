package com.quanweng.shopping.controller.admin;

import com.google.zxing.WriterException;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class GoodsController {
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

    @PostMapping("/goods")
    private Result createGoods(@RequestBody Goods goods) throws IOException, WriterException {
        Goods good = goodsService.createGoods(goods);
        log.info("创建新商品:{}",goods);
        return Result.success(good);
    }

    @PutMapping("/goods")
    private Result updateGoods(@RequestBody Goods goods){
        goodsService.updateGoods(goods);
        log.info("更改商品{}",goods);
        return Result.success();
    }

    @DeleteMapping("/goods/{id}")
    private Result deleteGoods(@PathVariable Long id){
        goodsService.deleteGoodsById(id);
        log.info("删除商品{}",id);
        return Result.success();
    }


    @PostMapping("/goodsByExcel/excel")
    private Result excelInput(@RequestParam String fileUrl) throws IOException {
        goodsService.excelInput(fileUrl);
        return Result.success();
    }

    @GetMapping("/goodsHaveTip")
    private Result getNoTip(){
        List<Goods> goodsList = goodsService.getAllGoodsByNoTip();
        return Result.success(goodsList);
    }


}
