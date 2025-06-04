package com.quanweng.shopping.controller.user

import com.quanweng.shopping.pojo.UserTrace
import com.quanweng.shopping.pojo.common.Result
import com.quanweng.shopping.service.GoodsService
import com.quanweng.shopping.service.UserTraceReqInfoService
import com.quanweng.shopping.service.UserTraceService
import com.quanweng.shopping.utils.UserTraceUtil
import jakarta.annotation.Nullable
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.time.LocalDateTime

@RestController
class GoodsUserController {
    // TODO() 一大堆
    companion object {
        private val log = LoggerFactory.getLogger(GoodsUserController::class.java)
    }

    @Autowired
    private val goodsService: GoodsService? = null

    @Autowired
    private val userTraceService: UserTraceService? = null

    @Autowired
    private val request: HttpServletRequest? = null

    @Autowired
    private val userTraceReqInfoService: UserTraceReqInfoService? = null

    @GetMapping("/goods")
    private fun getAllGoods(
        @RequestParam(defaultValue = "1") pages: Int?,
        @RequestParam(defaultValue = "20") size: Int?
    ): Result {
        val goodsList = goodsService!!.getAllGoods(pages, size)
        log.info("查看全部商品: $goodsList")
        return Result.success(goodsList)
    }

    @GetMapping("/goods/{category}")
    private fun getGoodsByCategory(
        @PathVariable category: String?,
        @RequestParam(defaultValue = "1") pages: Int?,
        @RequestParam(defaultValue = "20") size: Int?
    ): Result {
        val goodsList = goodsService!!.getGoodsByCategory(category, pages, size)
        log.info("查看該分類 $category 下的商品: $goodsList")
        return Result.success(goodsList)
    }

    @GetMapping("/goodsById/{id}")
    private fun getGoodsById(@PathVariable id: Long?, request: HttpServletRequest?): Result {
        val goods = goodsService!!.getGoodsById(id)

        val trace =
            UserTraceUtil.buildAndRecordUserTrace(request, "", "query_goodsById", "goodsId:$id", userTraceReqInfoService)
        userTraceService!!.recordTrace(trace)

        return Result.success(goods)
    }

    @PostMapping("/goodsByKeyWord")
    @Throws(IOException::class)
    private fun getGoodsByKeyWord(
        @RequestParam keyWord: String?,
        @RequestParam(required = false) @Nullable userId: Long?
    ): Result {
        val goodsList = goodsService!!.getGoodsByKeyWord(keyWord)
        if (userId != null) {
            goodsService.remarkTheKeyWord(keyWord, userId)
        }
        // 记录商品搜索痕迹
        val traceUserId = userId?.toString() ?: "NO_LOGIN"
        val trace = UserTrace()
        trace.userId = traceUserId
        trace.ip = request!!.remoteAddr
        trace.region = ""
        trace.action = "search"
        trace.actionData = "keyWord:$keyWord"
        trace.createTime = LocalDateTime.now()
        userTraceService!!.recordTrace(trace)

        return Result.success(goodsList)
    }

    @PostMapping("/goodsKeyWordList")
    private fun getGoodsKeyWordList(@RequestParam userId: Long?): Result {
        val goodsSearches = goodsService!!.getGoodsKeyWordList(userId)
        return Result.success(goodsSearches)
    }

    @GetMapping("/goodsHaveTip")
    private fun getNoTip(
        @RequestParam(defaultValue = "1") pages: Int?,
        @RequestParam(defaultValue = "20") size: Int?
    ): Result {
        val goodsList = goodsService!!.getAllGoodsByNoTip(pages, size)
        return Result.success(goodsList)
    }
}
