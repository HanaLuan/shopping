package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.OrderMapper;
import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.service.OrderService;
import com.quanweng.shopping.utils.ExpressLocalUtils;
import com.quanweng.shopping.utils.ExpressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ExpressUtils expressUtils;
    @Autowired
    private ExpressLocalUtils expressLocalUtils;


    @Override
    public List<Order> getAllOrder(Integer pages,Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Order> orderList = orderMapper.getAllOrder();
        return orderList;
    }

    @Override
    public Order createOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
//        if (order.getLogisticsId() != null) {
//            String com = expressLocalUtils.recognizeExpressCompany(order.getLogisticsId());
//            order.setLogisticsCom(com);
//        }
        orderMapper.createOrder(order);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        order.setUpdateTime(LocalDateTime.now());
//        if (order.getLogisticsId() != null) {
//            String com = expressUtils.getExpressCompany(order.getLogisticsId()).getFirst();
//            order.setLogisticsCom(com);
//        }
        orderMapper.updateOrder(order);
    }

    @Override
    public Integer getAllOrderCount() {
        return orderMapper.getAllOrderCount();
    }

    @Override
    public List<Order> getOrderByAdminId(Long orderFrom, Integer pages, Integer size) {
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Order> orderList = orderMapper.getOrderByAdminId(orderFrom);
        return orderList;
    }

    @Override
    public Integer getOrderByAdminIdCount(Long orderFrom) {
        return orderMapper.getOrderByAdminIdCount(orderFrom);
    }
}
