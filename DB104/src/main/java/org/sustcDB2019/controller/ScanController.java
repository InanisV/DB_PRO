package org.sustcDB2019.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.entity.GoodsInWarehouse;

import java.math.BigDecimal;

public class ScanController {


    public static void findWarehouseId(BigDecimal longi, BigDecimal lati){
        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        session.close();
    }

    public static void findGoodsByWarehouseId(Integer warehouseId){
        SqlSession session = DaoManager.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper mapper = session.getMapper(GoodsInWarehouseMapper.class);

    }

}
