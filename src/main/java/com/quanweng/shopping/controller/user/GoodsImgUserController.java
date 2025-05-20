package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.GoodsImg;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class GoodsImgUserController {
    @Autowired
    private GoodsImgService goodsImgService;

    @GetMapping("/goodsImg/{id}")
    private Result getGoodsImg(@PathVariable Long id){
        List<GoodsImg> goodsImgList = goodsImgService.getGoodsImgById(id);
        log.info("查询商品{}图片:{}",id,goodsImgList);
        return Result.success(goodsImgList);
    }
}
