package org.sustcDB2019.dao;

import org.apache.ibatis.annotations.Select;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

public interface WarehouseMapper {
    int deleteByPrimaryKey(Integer warehouseId);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Integer warehouseId);

    @Select("select * from warehouse")
    ArrayList<Warehouse> selectAll();

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);
}