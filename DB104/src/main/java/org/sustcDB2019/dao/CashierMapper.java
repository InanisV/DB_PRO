package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Cashier;

public interface CashierMapper {

    @Select("select max(user_id) from cashier")
    Integer selectMaxId();


    int deleteByPrimaryKey(Integer userId);

    int insert(Cashier record);

    int insertSelective(Cashier record);

    Cashier selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Cashier record);

    int updateByPrimaryKey(Cashier record);
}