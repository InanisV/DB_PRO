package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CashierMapper;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.dao.SalesMapper;
import org.sustcDB2019.entity.Cashier;
import org.sustcDB2019.entity.GoodsInWarehouse;
import org.sustcDB2019.entity.Sales;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CashierService {
    Cashier cashier;

    public int updateCasher(Cashier cashier){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CashierMapper mapper=sqlSession.getMapper(CashierMapper.class);//[add mapper] add casher's entity and mapper
        mapper.updateByPrimaryKeySelective(cashier);
        sqlSession.close();
        return 0;
    }

    //not completed
    public int addToCart(int goodsId,int amount){// or input goodsInWarehouse? (more close to reality)
        return 0;
    }

    public int cancleSales(ArrayList<Sales> list){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        for (Sales sales:list) {
            GoodsInWarehouse tmpGoodsInWarehouse=new GoodsInWarehouse();
            {
                tmpGoodsInWarehouse=goodsInWarehouseMapper.selectByPrimaryKey(sales.getGoodsInWarehouseId());
                tmpGoodsInWarehouse.setAmount(tmpGoodsInWarehouse.getAmount()+sales.getAmount());
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGoodsInWarehouse);
            }// can use a new update to simplefy this block
            salesMapper.deleteByPrimaryKey(sales.getSalesId());
        }
        sqlSession.close();
        return 0;
    }

    public BigDecimal buyOffline(ArrayList<Sales> list){//not completed
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        BigDecimal totalPayment=new BigDecimal(0);
        for (Sales sales:list) {
            sales.setIsPaid("Y");
            totalPayment.add(sales.getPayment());
            salesMapper.updateByPrimaryKeySelective(sales);
        }
        goodsInWarehouseMapper.deleteAll();
        sqlSession.close();
        return totalPayment;
    }
}
