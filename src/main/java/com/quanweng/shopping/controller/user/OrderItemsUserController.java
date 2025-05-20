package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.OrderItems;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.OrderItemsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OrderItemsUserController {
    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/orderItems")
    private Result getAllOrderItems(){
        List<OrderItems> orderItemsList = orderItemsService.getAllOrderItems();
        log.info("查询全部订单商品:{}",orderItemsList);
        return Result.success(orderItemsList);
    }

    @GetMapping("/orderItems/{id}")
    private Result getOrderItemByOrderId(@PathVariable Long id){
        List<OrderItems> orderItemsList = orderItemsService.getOrderItemByOrderId(id);
        log.info("根据订单号查询商品:{}",orderItemsList);
        return Result.success(orderItemsList);
    }

    @PostMapping("/orderItems")
    private Result createOrderItems(@RequestBody OrderItems orderItems){
        orderItemsService.createOrderItems(orderItems);
        log.info("新建订单商品:{}",orderItems);
        return Result.success();
    }

    @PutMapping("/orderItems")
    private Result updateOrderItems(@RequestBody OrderItems orderItems){
        orderItemsService.updateOrderItems(orderItems);
        log.info("更改新建商品:{}",orderItems);
        return Result.success();
    }

}
