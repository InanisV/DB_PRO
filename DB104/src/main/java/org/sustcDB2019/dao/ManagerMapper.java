package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Manager;
import org.sustcDB2019.entity.Sales;

public interface ManagerMapper {
    //need to be test
    @Select("select max(user_id) from manager")
    int selectMaxId();


    int deleteByPrimaryKey(Integer userId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}