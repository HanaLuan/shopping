package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    private Result getAllOrder(){
        List<Order> orderList = orderService.getAllOrder();
        log.info("查询全部订单:{}",orderList);
        return Result.success(orderList);
    }

    @PostMapping("/order")
    private Result createOrder(@RequestBody Order order){
        orderService.createOrder(order);
        log.info("创建新订单:{}",order);
        return Result.success();
    }

    @PutMapping("/order")
    private Result updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        log.info("更新订单:{}",order);
        return Result.success();
    }
}
