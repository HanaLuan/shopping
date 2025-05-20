package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.OrderItemsMapper;
import com.quanweng.shopping.mapper.OrderMapper;
import com.quanweng.shopping.pojo.OrderItems;
import com.quanweng.shopping.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Override
    public List<OrderItems> getAllOrderItems() {
        return orderItemsMapper.getAllOrderItems();
    }

    @Override
    public void createOrderItems(OrderItems orderItems) {
        orderItems.setCreateTime(LocalDateTime.now());
        orderItems.setUpdateTime(LocalDateTime.now());
        orderItemsMapper.createOrderItems(orderItems);
    }

    @Override
    public void updateOrderItems(OrderItems orderItems) {
        orderItems.setUpdateTime(LocalDateTime.now());
        orderItemsMapper.updateOrderItems(orderItems);
    }

    @Override
    public List<OrderItems> getOrderItemByOrderId(Long id) {
        return orderItemsMapper.getOrderItemByOrderId(id);
    }
}
