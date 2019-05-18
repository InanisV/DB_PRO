package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Order;

import java.util.ArrayList;

public interface OrderMapper {
    //need to be test
    ArrayList<Order> selectByCase(Order order);


    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}