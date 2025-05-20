package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.OrderItems;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemsMapper {
    @Select("select * from order_items_table")
    List<OrderItems> getAllOrderItems();

    void createOrderItems(OrderItems orderItems);

    void updateOrderItems(OrderItems orderItems);

    @Select("select * from order_items_table where order_id = #{id}")
    List<OrderItems> getOrderItemByOrderId(Long id);
}
