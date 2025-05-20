package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Cart;

import java.util.List;

public interface CartService {
    void addCart(Cart cart);

    void subCart(Cart cart);

    List<Cart> cartList(Long userId);

    void cleanCart(Long userId);
}
