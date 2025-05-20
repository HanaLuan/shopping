package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<Cart> findCart(Cart cart);

    void updateNumber(Cart theCart);

    void createCart(Cart cart);

    void deleteCartById(Long id);

    void cleanCart(Long userId);
}
