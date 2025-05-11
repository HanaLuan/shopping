package com.quanweng.shopping.controller;

import com.quanweng.shopping.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
}
