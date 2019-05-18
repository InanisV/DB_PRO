package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Deliverer;

import java.util.ArrayList;

public interface DelivererMapper {
    //tested
    ArrayList<Deliverer> selectByCase(Deliverer deliverer);


    int deleteByPrimaryKey(Integer userId);

    int insert(Deliverer record);

    int insertSelective(Deliverer record);

    Deliverer selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Deliverer record);

    int updateByPrimaryKey(Deliverer record);
}