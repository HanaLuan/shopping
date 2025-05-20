package com.quanweng.shopping.controller;


import com.quanweng.shopping.pojo.Cart;
import com.quanweng.shopping.pojo.User;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    private Result addCart(@RequestBody Cart cart){
        cartService.addCart(cart);
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
