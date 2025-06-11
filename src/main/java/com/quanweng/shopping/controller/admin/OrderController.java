package com.quanweng.shopping.controller.admin;

import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.GroupService;
import com.quanweng.shopping.service.OrderService;
import com.quanweng.shopping.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/order")
    private Result getAllOrder(@RequestParam(required = false) Integer pages,
                               @RequestParam(required = false) Integer size){

        List<Order> orderList = orderService.getAllOrder(pages,size);
        log.info("查询全部订单:{}",orderList);
        Integer total = orderService.getAllOrderCount();
        return Result.success(Map.of("total",total,"list",orderList));
    }

    @PostMapping("/order")
    private Result createOrder(@RequestBody Order order){
        Order afterOrder = orderService.createOrder(order);
        log.info("创建新订单:{}",order);
        return Result.success(afterOrder);
    }

    @PutMapping("/order")
    private Result updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        log.info("更新订单:{}",order);
        return Result.success();
    }

    @GetMapping("/orderByAdminId")
    private Result getOrderByAdminId(@RequestParam Long orderFrom,
                                     @RequestParam(required = false) Integer pages,
                                     @RequestParam(required = false) Integer size){
        List<Order> orderList = orderService.getOrderByAdminId(orderFrom,pages,size);
        Integer total = orderService.getOrderByAdminIdCount(orderFrom);
        return Result.success(Map.of("total",total,"list",orderList));
    }
}
