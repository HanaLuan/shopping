package com.quanweng.shopping.controller;


import com.quanweng.shopping.pojo.Cart;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CartService;
import com.quanweng.shopping.service.UserTraceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserTraceService userTraceService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addCart")
    private Result addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
        // 记录加入购物车痕迹
        String userId = cart.getUserId() != null ? cart.getUserId().toString() : "NO_LOGIN";
        UserTrace trace = new UserTrace();
        trace.setUserId(userId);
        trace.setIp(request.getRemoteAddr());
        trace.setRegion(""); // 地区预留
        trace.setAction("add_cart");
        trace.setActionData("goodsId:" + cart.getGoodsId());
        userTraceService.recordTrace(trace);
        return Result.success();
    }

    @PostMapping("/subCart")
    private Result subCart(@RequestBody Cart cart){
        cartService.subCart(cart);
        return Result.success();
    }

    @PostMapping("/cartList")
    private Result cartList(@RequestParam Long userId){
        List<Cart> cartList = cartService.cartList(userId);
        return Result.success(cartList);
    }

    @DeleteMapping("/cleanCart")
    private Result cleanCart(@RequestParam Long userId){
        cartService.cleanCart(userId);
        return Result.success();
    }
}
