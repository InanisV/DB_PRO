package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Deliverer;

public interface DelivererMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Deliverer record);

    int insertSelective(Deliverer record);

    Deliverer selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Deliverer record);

    int updateByPrimaryKey(Deliverer record);
}