package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.CartMapper;
import com.quanweng.shopping.mapper.GoodsMapper;
import com.quanweng.shopping.pojo.Cart;
import com.quanweng.shopping.pojo.Goods;
import com.quanweng.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addCart(Cart cart) {
        List<Cart> cartList = cartMapper.findCart(cart);
        if (cartList != null && !cartList.isEmpty()){
            Cart theCart = cartList.get(0);
            theCart.setNumber(theCart.getNumber() + 1);
            theCart.setUpdateTime(LocalDateTime.now());
            cartMapper.updateNumber(theCart);
        }else {
            cart.setCreateTime(LocalDateTime.now());
            cart.setUpdateTime(LocalDateTime.now());
            cart.setNumber(1);
            Goods goods = goodsMapper.getGoodsByGoodsId(cart.getGoodsId());
            cart.setGoodsUrl(goods.getGoodsShowImg());
            cart.setGoodsName(goods.getGoodsName());
            cartMapper.createCart(cart);
        }
    }

    @Override
    public void subCart(Cart cart) {
        List<Cart> cartList = cartMapper.findCart(cart);
        if (cartList != null && !cartList.isEmpty()){
            Cart theCart = cartList.get(0);
            if(theCart.getNumber() == 1){
                cartMapper.deleteCartById(theCart.getId());
            }else if (theCart.getNumber() > 1){
                theCart.setUpdateTime(LocalDateTime.now());
                theCart.setNumber(theCart.getNumber() - 1);
                cartMapper.updateNumber(theCart);
            }
        }
    }

    @Override
    public List<Cart> cartList(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartMapper.findCart(cart);
    }

    @Override
    public void cleanCart(Long userId) {
        cartMapper.cleanCart(userId);
    }
}
