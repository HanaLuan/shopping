package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GoodsService;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import com.quanweng.shopping.service.UserTraceService;
import com.quanweng.shopping.utils.UserTraceUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.quanweng.shopping.utils.UserTraceUtil.getUserIdFromHeader;

@RestController
public class GoodsUserController {

    private static final Logger log = LoggerFactory.getLogger(GoodsUserController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserTraceService userTraceService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;

    @GetMapping("/goods")
    private Result getAllGoods(
            @RequestParam(defaultValue = "1") Integer pages,
            @RequestParam(defaultValue = "20") Integer size) {
        var goodsList = goodsService.getAllGoods(pages, size);
        log.info("查看全部商品: {}", goodsList);
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_AllGoods",
                "" + goodsList,
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goodsList);
    }

    @GetMapping("/goods/{category}")
    private Result getGoodsByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "1") Integer pages,
            @RequestParam(defaultValue = "20") Integer size) {
        var goodsList = goodsService.getGoodsByCategory(category, pages, size);
        log.info("查看該分類 {} 下的商品: {}", category, goodsList);
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_SpecCategoryGoods",
                "categories:" + category + "\r\n" + "goodsList:" + goodsList,
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goodsList);
    }

    @GetMapping("/goodsById/{id}")
    private Result getGoodsById(@PathVariable Long id, HttpServletRequest req) {
        var goods = goodsService.getGoodsById(id);
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                req,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_goodsById",
                "goodsId:" + id,
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goods);
    }

    @PostMapping("/goodsByKeyWord")
    private Result getGoodsByKeyWord(
            @RequestParam String keyWord,
            @RequestParam(required = false) @Nullable Long userId) throws IOException {
        var goodsList = goodsService.getGoodsByKeyWord(keyWord);
        if (userId != null) {
            goodsService.remarkTheKeyWord(keyWord, userId);
        }
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_GoodsByKeyWord",
                "keyWord:" + keyWord,
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goodsList);
    }

    @PostMapping("/goodsKeyWordList")
    private Result getGoodsKeyWordList(@RequestParam Long userId) {
        var goodsSearches = goodsService.getGoodsKeyWordList(userId);
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_GoodsKeyWordList",
                "",
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goodsSearches);
    }

    @GetMapping("/goodsHaveTip")
    private Result getNoTip(
            @RequestParam(defaultValue = "1") Integer pages,
            @RequestParam(defaultValue = "20") Integer size) {
        var goodsList = goodsService.getAllGoodsByNoTip(pages, size);
        var trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "query_GoodsTip",
                "",
                userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(goodsList);
    }
}
