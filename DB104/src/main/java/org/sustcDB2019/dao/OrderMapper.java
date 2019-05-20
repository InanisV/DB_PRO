package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Order;

import java.util.ArrayList;

public interface OrderMapper {
    @Select("select max(o.order_id) from `order` o;")
    int selectMaxId();

    //need to be test
    ArrayList<Order> selectByCase(Order order);


    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}