package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Customer;

public interface CustomerMapper {
    @Select("select max(user_id) from customer")
    Integer selectMaxId();

    int deleteByPrimaryKey(Integer userId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}