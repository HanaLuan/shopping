package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select * from order_table order by create_time desc ")
    List<Order> getAllOrder();


    Long createOrder(Order order);

    void updateOrder(Order order);

    @Select("select count(*) from order_table")
    Integer getAllOrderCount();

    @Select("select * from order_table where order_from = #{orderFrom} order by create_time desc")
    List<Order> getOrderByAdminId(Long orderFrom);

    @Select("select count(*) from order_table where order_from = #{orderFrom}")
    Integer getOrderByAdminIdCount(Long orderFrom);


    List<Order> getAllOrderByGroupId(@Param("groupIds") List<Long> groupIds);
}
