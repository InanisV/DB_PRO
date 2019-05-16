package org.sustcDB2019.dao;

import org.sustcDB2019.entity.GoodsInWarehouse;

public interface GoodsInWarehouseMapper {
    int deleteByPrimaryKey(Integer idgoodsInWarehouse);

    int insert(GoodsInWarehouse record);

    int insertSelective(GoodsInWarehouse record);

    GoodsInWarehouse selectByPrimaryKey(Integer idgoodsInWarehouse);

    int updateByPrimaryKeySelective(GoodsInWarehouse record);

    int updateByPrimaryKey(GoodsInWarehouse record);
}