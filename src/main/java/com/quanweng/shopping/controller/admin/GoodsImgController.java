package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class GoodsImgController {
    @Autowired
    private GoodsImgService goodsImgService;

    @GetMapping("/goodsImg/{id}")
    private Result getGoodsImg(@PathVariable Long id){
        List<GoodsImg> goodsImgList = goodsImgService.getGoodsImgById(id);
        log.info("查询商品{}图片:{}",id,goodsImgList);
        return Result.success(goodsImgList);
    }

    @PostMapping("/goodsImgMore")
    private Result createGoodsImgMore(@RequestBody List<GoodsImg> goodsImgList){
        goodsImgService.createGoodsImgMore(goodsImgList);
        return Result.success();
    }

    @PostMapping("/goodsImg")
    private Result createGoodsImg(@RequestBody GoodsImg goodsImg){
        goodsImgService.createGoodsImg(goodsImg);
        return Result.success();
    }

    @PutMapping("/goodsImg")
    private Result updateGoodsImg(@RequestBody GoodsImg goodsImg){
        goodsImgService.updateGoodsImg(goodsImg);
        return Result.success();
    }

    @DeleteMapping("/goodsImg/{id}")
    private Result deleteGoodsImg(@PathVariable Long id){
        goodsImgService.deleteGoodsImg(id);
        return Result.success();
    }
}
