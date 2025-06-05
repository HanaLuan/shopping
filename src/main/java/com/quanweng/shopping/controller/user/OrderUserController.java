package com.quanweng.shopping.controller.user;

import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.pojo.UserTrace;
import com.quanweng.shopping.pojo.common.Result;
import com.quanweng.shopping.service.OrderService;
import com.quanweng.shopping.service.UserTraceReqInfoService;
import com.quanweng.shopping.service.UserTraceService;
import com.quanweng.shopping.utils.UserTraceUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OrderUserController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserTraceReqInfoService userTraceReqInfoService;
    @Autowired
    private UserTraceService userTraceService;

    @GetMapping("/order")
    private Result getAllOrder(@RequestParam(required = false) Integer pages,
                               @RequestParam(required = false) Integer size){
        List<Order> orderList = orderService.getAllOrder(pages, size);
        log.info("查询全部订单:{}",orderList);
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "order_queryOrderList",
                "order:" + orderList, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(orderList);
    }

    @PostMapping("/order")
    private Result createOrder(@RequestBody Order order){
        Order afterOrder = orderService.createOrder(order);
        log.info("创建新订单:{}",order);
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "order_createOrder",
                "order:" + order, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success(afterOrder);
    }


    @PutMapping("/order")
    private Result updateOrder(@RequestBody Order order){
        orderService.updateOrder(order);
        log.info("更新订单:{}",order);
        UserTrace trace = UserTraceUtil.buildAndRecordUserTrace(
                request,
                UserTraceUtil.getUserIdFromHeader(request),
                "order_updateOrder",
                "order:" + order, userTraceReqInfoService);
        userTraceService.recordTrace(trace);
        return Result.success();
    }
}
