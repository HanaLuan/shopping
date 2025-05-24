package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.OrderMapper;
import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrder() {
        return orderMapper.getAllOrder();
    }

    @Override
    public Order createOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderMapper.createOrder(order);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        order.setUpdateTime(LocalDateTime.now());
        orderMapper.updateOrder(order);
    }
}
