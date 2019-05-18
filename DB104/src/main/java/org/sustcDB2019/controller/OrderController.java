package org.sustcDB2019.controller;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.dao.GoodsMapper;
import org.sustcDB2019.entity.Goods;
import org.sustcDB2019.entity.Sales;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

public class OrderController {
    //购买的逻辑：根据仓库来查询货物，分页
    //选择货物看更详细信息，或直接加入购物车
    //要对购买的货物算一个总价，再加入order
    public static SqlSession session = DaoManager.sqlSessionFactory.openSession();

    public static ArrayList<Goods> showGoodsByWarehouse(Integer warehouseId, int pages) {
        GoodsInWarehouseMapper mapper = session.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<Goods> goods = mapper.selectConditionallyWithPages(
                warehouseId.toString(), null, null, null, null, null,
                null, null, null, false, null,
                false, 10, pages-1);
        return goods;
    }

    public static boolean addGoods(Sales sale){
        
        if(sale.getIsPaid()=="Y"){

        }
        Sales
    }


}