package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Manager;

public interface ManagerMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}