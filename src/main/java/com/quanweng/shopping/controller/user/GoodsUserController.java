package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.mapper.UserTraceReqInfoMapper;
import com.quanweng.shopping.pojo.*;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import com.quanweng.shopping.service.UserTraceService;
import com.quanweng.shopping.utils.UserTraceUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
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
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;


    @GetMapping("/goods")
    private Result getAllGoods(@RequestParam(required = false) Integer pages,
                               @RequestParam(required = false) Integer size){
        List<Goods> goodsList = goodsService.getAllGoods(pages,size);
        log.info("查看全部商品:{}",goodsList);
        return Result.success(goodsList);
    }

    @GetMapping("/goods/{category}")
    private Result getGoodsByCategory(@PathVariable String category,
                                      @RequestParam(required = false) Integer pages,
                                      @RequestParam(required = false) Integer size){
        List<Goods> goodsList = goodsService.getGoodsByCategory(category,pages,size);
        log.info("查看该分类{}下的商品:{}",category,goodsList);
        return Result.success(goodsList);
    }

    @GetMapping("/goodsById/{id}")
    private Result getGoodsById(@PathVariable Long id, HttpServletRequest request) {
        Goods goods = goodsService.getGoodsById(id);

        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(request, id, userTraceReqInfoService);
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
    private Result getNoTip(@RequestParam(defaultValue = "1") Integer pages,
                            @RequestParam(defaultValue = "20") Integer size){
        List<Goods> goodsList = goodsService.getAllGoodsByNoTip(pages,size);
        return Result.success(goodsList);
    }


}
