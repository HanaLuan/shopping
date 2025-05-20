package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrder();

    void createOrder(Order order);

    void updateOrder(Order order);
}
