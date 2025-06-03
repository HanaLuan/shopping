package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order_table order by create_time desc ")
    List<Order> getAllOrder();


    Long createOrder(Order order);

    void updateOrder(Order order);
}
