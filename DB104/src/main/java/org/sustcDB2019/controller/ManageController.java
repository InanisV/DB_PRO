package org.sustcDB2019.controller;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.dao.GoodsMapper;
import org.sustcDB2019.dao.WarehouseMapper;
import org.sustcDB2019.entity.Goods;
import org.sustcDB2019.entity.GoodsInWarehouse;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;


public class ManageController {
    //添加purchase的基本流程为：选择仓库-》选择货物信息（刷新的选择框）或新建货物信息-》检查仓库容量是否符合货物要求-》添加或失败

    public static void purchaseGoods(){}

    //查询某一个仓库的货架剩余容量，选择仓库，查询
    public static void queryVolume(Warehouse warehouse){

    }

    public static ArrayList<Warehouse> showWarehouse(){
        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        WarehouseMapper mapper = session.getMapper(WarehouseMapper.class);
        ArrayList<Warehouse> warehouses = mapper.selectAll();
        session.close();
        return warehouses;
    }

    //goods 可以不完整，返回符合的完整goods。
    public static ArrayList<Goods> findGoodsByCondition(Goods goods){
        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
        ArrayList<Goods> goods1 = mapper.selectConditionally(goods);
        session.close();
        return goods1;
    }

    //attribute should not be null
    public static void insertGoods(Goods goods){
        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
        mapper.insert(goods);
        session.close();
    }

    public static void insertGoodsInWarehouse(GoodsInWarehouse goodsInWarehouse) {

        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper mapper = session.getMapper(GoodsInWarehouseMapper.class);
        mapper.insert(goodsInWarehouse);
        session.close();
    }
}