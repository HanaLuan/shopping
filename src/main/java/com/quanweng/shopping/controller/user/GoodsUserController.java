package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.pojo.GoodsSearch;
import com.quanweng.shopping.pojo.Translate;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import com.quanweng.shopping.service.UserTraceService;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
public class GoodsUserController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserTraceService userTraceService;
    @Autowired
    private HttpServletRequest request;


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
        // 记录商品浏览痕迹
        String userId = request.getHeader("userId");
        if (userId == null || userId.isEmpty()) userId = "NO_LOGIN";
        UserTrace trace = new UserTrace();
        trace.setUserId(userId);
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 地区预留
        trace.setAction("navigation");
        trace.setActionData("goodsId:" + id);
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);
        return Result.success(goods);
    }

    @PostMapping("/goodsByKeyWord")
    private Result getGoodsByKeyWord(@RequestParam String keyWord,@RequestParam(required = false) @Nullable Long userId) throws IOException {
        List<Goods> goodsList = goodsService.getGoodsByKeyWord(keyWord);
        if (userId != null){
            goodsService.remarkTheKeyWord(keyWord,userId);
        }
        // 记录商品搜索痕迹
        String traceUserId = userId != null ? userId.toString() : "NO_LOGIN";
        UserTrace trace = new UserTrace();
        trace.setUserId(traceUserId);
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 地区预留
        trace.setAction("search");
        trace.setActionData("keyWord:" + keyWord);
        trace.setCreateTime(java.time.LocalDateTime.now());
        userTraceService.recordTrace(trace);


        return Result.success(goodsList);
    }

    @PostMapping("/goodsKeyWordList")
    private Result getGoodsKeyWordList(@RequestParam Long userId){
        List<GoodsSearch> goodsSearches = goodsService.getGoodsKeyWordList(userId);
        return Result.success(goodsSearches);
    }

    @GetMapping("/goodsHaveTip")
    private Result getNoTip(){
        List<Goods> goodsList = goodsService.getAllGoodsByNoTip();
        return Result.success(goodsList);
    }
}
