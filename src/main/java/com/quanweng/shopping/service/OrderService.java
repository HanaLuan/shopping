package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder(Integer pages,Integer size);

    Order createOrder(Order order);

    void updateOrder(Order order);

    Integer getAllOrderCount();

    List<Order> getOrderByAdminId(Long orderFrom, Integer pages, Integer size);

    Integer getOrderByAdminIdCount(Long orderFrom);
}
