package com.quanweng.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.quanweng.shopping.mapper.AdminMapper;
import com.quanweng.shopping.mapper.GroupMapper;
import com.quanweng.shopping.mapper.OrderMapper;
import com.quanweng.shopping.pojo.Admin;
import com.quanweng.shopping.pojo.Group;
import com.quanweng.shopping.pojo.Order;
import com.quanweng.shopping.service.GroupService;
import com.quanweng.shopping.service.OrderService;
import com.quanweng.shopping.utils.ExpressLocalUtils;
import com.quanweng.shopping.utils.ExpressUtils;
import com.quanweng.shopping.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ExpressUtils expressUtils;
    @Autowired
    private ExpressLocalUtils expressLocalUtils;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GroupService groupService;
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public List<Order> getAllOrder(Integer pages,Integer size) {
        String adminName = "";
        String token = request.getHeader("token");
        log.info("{}",token);
        if(token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7).trim();
            }
        }
        if(token != null && !token.isEmpty()){
            try{
                Object uid = JWTUtils.parseToken(token).get("adminName");
                if(uid != null) adminName = uid.toString();
            }catch (Exception ignore){}
        }
        log.info("{}",adminName);
        Admin admin = adminMapper.getAdminByName(adminName);
        if(admin.getAdminLevel() == 0){
            if (pages != null && size != null) {
                PageHelper.startPage(pages, size);
            }
            List<Order> orderList = orderMapper.getAllOrder();
            return orderList;
        }
        List<Group> groupList = groupService.getAdminGroupByAdminId(admin.getId());
        log.info("{}",groupList);
        if(groupList == null || groupList.isEmpty()){
            return List.of();
        }
        List<Long> groupIds = new ArrayList<>();
        for(Group group:groupList){
            groupIds.add(group.getId());
        }
        if (pages != null && size != null) {
            PageHelper.startPage(pages, size);
        }
        List<Order> orderList = orderMapper.getAllOrderByGroupId(groupIds);
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
