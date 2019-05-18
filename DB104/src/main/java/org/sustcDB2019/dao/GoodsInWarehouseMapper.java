package org.sustcDB2019.dao;

import org.sustcDB2019.entity.Goods;
import org.sustcDB2019.entity.GoodsInWarehouse;

import java.util.ArrayList;

public interface GoodsInWarehouseMapper {
    //need to be test
    ArrayList<Goods> selectWithPages(int warehouseId, int itemsPerPage, int pageIndex);
    //need to be test
    ArrayList<Goods> selectConditionallyWithPages(
            String warehouseId, String type, String catagory,
            String name, String brand, String orginPlace,
            String refrigiratedCondition, String lowerPrice, String upperPrice,
            boolean discount, String orderByPriceIncrease, boolean orderByDiscount,
            int itemsPerPage, int pageIndex);

    ArrayList<GoodsInWarehouse> selectByCase(int warehouseWarehouseId, int goodsGoodsId);
//    ArrayList<GoodsInWarehouse> selectConditionally(GoodsInWarehouse goodsInWarehouse);
//    ArrayList<GoodsInWarehouse> selectConditionally(
//            String warehouseId,String goodsId, String type, String catagory,
//            String name, String brand, String orginPlace,
//            String refrigiratedCondition, String lowerPrice, String upperPrice,
//            boolean discount, String orderByPriceIncrease, boolean orderByDiscount,boolean orderByExpiredDay);


    int deleteByPrimaryKey(Integer idgoodsInWarehouse);

    int insert(GoodsInWarehouse record);

    int insertSelective(GoodsInWarehouse record);

    GoodsInWarehouse selectByPrimaryKey(Integer idgoodsInWarehouse);

    int updateByPrimaryKeySelective(GoodsInWarehouse record);

    int updateByPrimaryKey(GoodsInWarehouse record);
}