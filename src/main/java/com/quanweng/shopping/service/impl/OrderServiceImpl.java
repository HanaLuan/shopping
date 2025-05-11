package com.quanweng.shopping.service.impl;

import com.quanweng.shopping.mapper.OrderMapper;
import com.quanweng.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
}
