package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Deliverer;

import java.util.ArrayList;

public interface DelivererMapper {
    //tested
    ArrayList<Deliverer> selectByCase(Deliverer deliverer);

    @Select("select max(user_id) from deliverer")
    Integer selectMaxId();


    int deleteByPrimaryKey(Integer userId);

    int insert(Deliverer record);

    int insertSelective(Deliverer record);

    Deliverer selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Deliverer record);

    int updateByPrimaryKey(Deliverer record);
}