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

<<<<<<< HEAD
=======
    ArrayList<GoodsInWarehouse> selectByCase(int goodsId, int warehouseId);
>>>>>>> c04d47b4b217fa57890278fa03ad19d7e7682d82
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