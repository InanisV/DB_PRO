package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CashierMapper;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.dao.GoodsMapper;
import org.sustcDB2019.dao.SalesMapper;
import org.sustcDB2019.entity.Cashier;
import org.sustcDB2019.entity.Goods;
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


    public int addToCart(int goodsId, int amount) {
//        Sales sales=new Sales();
//        sales.setAmount(amount);
//        sales.setGoodsInWarehouseId(goodsId);
//        sales.setIsPaid("N");
//        sales.setCustomerUserId(customer.getId());
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
//        BigDecimal amountDecimal=new BigDecimal(amount);
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<GoodsInWarehouse> list = goodsInWarehouseMapper.selectByCase(goodsId, cashier.getWarehouseWarehouseId());
        int rest = 0;
        for (GoodsInWarehouse goodsInWarehouse : list) {
            rest += goodsInWarehouse.getAmount();
        }
        if (rest < amount) return 1;// fail to add to cart since amount excceed
        int tmpAmount = amount;
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);
        for (int i = 0; i < list.size(); i++) {
            Sales tmpSales = new Sales();
            GoodsInWarehouse tmpGIW = list.get(i);
            if (tmpGIW.getAmount() >= tmpAmount) {
                tmpSales.setAmount(tmpAmount);
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
//                tmpSales.setCustomerUserId(cashier.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpAmount)));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(tmpGIW.getAmount() - tmpAmount);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
                break;
            } else {
                tmpSales.setAmount(tmpGIW.getAmount());
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
                tmpSales.setCustomerUserId(cashier.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpGIW.getAmount())));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(0);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
            }
        }
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
        return 0;
    }


}
