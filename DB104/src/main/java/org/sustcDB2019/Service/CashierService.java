package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CashierService extends UserService{
    public Cashier cashier=new Cashier();

    public int updateCashier(Cashier cashier){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CashierMapper mapper=sqlSession.getMapper(CashierMapper.class);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User tmpUser=new User(cashier);

        if (tmpUser.getPassword()!=null&&tmpUser.getUserName()!=null&&tmpUser.getPhoneNumber()!=null){
            userMapper.updateByPrimaryKeySelective(tmpUser);
            sqlSession.commit();
        }

        if (cashier.getWarehouseWarehouseId()!=null){
            mapper.updateByPrimaryKeySelective(cashier);
        }

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }

    public BigDecimal buyOffline(ArrayList<Sales> list, Date currentDate){//not completed
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);

        BigDecimal totalPayment=new BigDecimal(0);
        for (Sales sales:list) {
            sales.setIsPaid("Y");
            totalPayment.add(sales.getPayment());
            sales.setSalesTime(currentDate);
            salesMapper.updateByPrimaryKeySelective(sales);
        }
        sqlSession.commit();
        goodsInWarehouseMapper.deleteAll();

        sqlSession.commit();
            sqlSession.close();
        return totalPayment;
    }


    public int addToCart(int goodsId, int amount) {
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        SalesMapper salesMapper = sqlSession.getMapper(SalesMapper.class);

        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        ArrayList<GoodsInWarehouse> list = goodsInWarehouseMapper.selectByCase(goodsId, cashier.getWarehouseWarehouseId());
        int rest = 0;
        for (GoodsInWarehouse goodsInWarehouse : list) {
            rest += goodsInWarehouse.getAmount();
        }
        if (rest < amount) {
            sqlSession.commit();
            sqlSession.close();
            return 1;// fail to add to cart since amount excceed
        }
        int tmpAmount = amount;
        for (int i = 0; i < list.size(); i++) {
            Sales tmpSales = new Sales();
            GoodsInWarehouse tmpGIW = list.get(i);
            if (tmpGIW.getAmount() >= tmpAmount) {
                tmpSales.setAmount(tmpAmount);
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
                tmpSales.setCustomerUserId(cashier.getId());
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

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }

    public ArrayList<Sales> showCart(Integer userId) {
        SqlSession session = DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper = session.getMapper(SalesMapper.class);

        Sales sale = new Sales();
        sale.setCustomerUserId(userId);
        sale.setIsPaid("N");
        ArrayList<Sales> result = salesMapper.selectByCase(sale);

        session.close();
        return result;
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
            }
            salesMapper.deleteByPrimaryKey(sales.getSalesId());
        }

        sqlSession.commit();
            sqlSession.close();
        return 0;
    }


}
