package com.quanweng.shopping.service;

import com.quanweng.shopping.pojo.OrderItems;

import java.util.List;

public interface OrderItemsService {
    List<OrderItems> getAllOrderItems();

    void createOrderItems(OrderItems orderItems);

    void updateOrderItems(OrderItems orderItems);

    List<OrderItems> getOrderItemByOrderId(Long id);
}
