package com.quanweng.shopping.controller.admin;

import com.google.zxing.WriterException;
import com.qiniu.common.QiniuException;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import com.quanweng.shopping.utils.UserTraceUtil;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods")
    private Result getAllGoods(@RequestParam(required = false) Integer pages,
                               @RequestParam(required = false) Integer size){
        List<Goods> goodsList = goodsService.getAllGoods(pages,size);
        log.info("查看全部商品:{}",goodsList);
        Integer total = goodsService.getAllGoodsCount();
        return Result.success(Map.of("total",total,"list",goodsList));
    }

    @GetMapping("/goods/{category}")
    private Result getGoodsByCategory(@PathVariable String category,
                                      @RequestParam(required = false) Integer pages,
                                      @RequestParam(required = false) Integer size){
        List<Goods> goodsList = goodsService.getGoodsByCategory(category,pages,size);
        log.info("查看该分类{}下的商品:{}",category,goodsList);
        Integer total = goodsService.getGoodsByCategoryCount(category);
        return Result.success(Map.of("total",total,"list",goodsList));
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
    private Result updateGoods(@RequestBody Goods goods) throws IOException, WriterException {
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
    private Result getNoTip(@RequestParam(required = false) Integer pages,
                            @RequestParam(required = false) Integer size){
        List<Goods> goodsList = goodsService.getAllGoodsByNoTip(pages,size);
        Integer total = goodsService.getAllGoodsByNoTipCount();
        return Result.success(Map.of("total",total,"list",goodsList));
    }

    @PostMapping("/goodsByKeyWord")
    private Result getGoodsByKeyWord(
            @RequestParam String keyWord,
            @RequestParam(required = false) Integer pages,
            @RequestParam(required = false) Integer size) throws IOException {
        var goodsList = goodsService.getGoodsByKeyWord(keyWord,pages,size);
        Integer total = goodsService.getGoodsByKeyWordCount(keyWord);
        return Result.success(Map.of("total",total,"list",goodsList));
    }

    @PostMapping("/uploadImg")
    private Result uploadImg() throws IOException, WriterException {
        goodsService.uploadImg();
        return Result.success();
    }


}
