package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long orderId);

    List<Order> getAllOrder(Integer pages, Integer size);

    Order createOrder(Order order);

    void updateOrder(Order order);

    Integer getAllOrderCount();

    List<Order> getOrderByAdminId(Long orderFrom, Integer pages, Integer size);

    Integer getOrderByAdminIdCount(Long orderFrom);

    List<Order> getOrderByPhoneOrEmail(String phoneOrEmail, Integer pages, Integer size);

    Integer getOrderByPhoneOrEmailCount(String phoneOrEmail);
}
