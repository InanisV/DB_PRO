package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.lang.Math.*;

public class CustomerService extends UserService {
    public Customer customer = (Customer) super.user;

    public int updateCustomer() {
        return 0;
    }

    public void updateWarehouse() {//start when logitude and latitude are changed or user is just sign in
        ArrayList<Warehouse> warehouses = WarehouseService.getAllWarehouse();
        double R = 6371.0;
        int minWarehouseId = 0;
        double minDistance = 0;
        for (int i = 0; i < warehouses.size(); i++) {
            double phi1 = (90 - customer.getCustomerLati().doubleValue()) * PI / 180, phi2 = (90 - warehouses.get(i).getWarehouseLati().doubleValue()) * PI / 180;
            double c = sin(phi1) * sin(phi2) * cos((customer.getCustomerLong().doubleValue() - warehouses.get(i).getWarehouseLong().doubleValue()) * PI / 180) + cos(phi1) *
                    cos(phi2);
            double d = R * acos(c);
            if (d < minDistance) {
                minDistance = d;
                minWarehouseId = i;
            }
        }
        customer.setWarehouseId(minWarehouseId);
    }

//
//    public ArrayList<Goods> goodsArrayList() {
//
//    }

    public ArrayList<Goods> goodsArrayListWithFilter(Goods filterGoods, int warehouseId, String lowerPerice, String upperPirce, boolean discount, String orderByPriceIncrease, boolean orderByDiscount, int index) {//
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<Goods> list = goodsInWarehouseMapper.selectConditionallyWithPages(
                customer.getWarehouseId() > 0 ? String.format("%d", customer.getWarehouseId()) : null, filterGoods.getType(),
                filterGoods.getCatagory(),
                filterGoods.getName(),
                filterGoods.getBrand(),
                filterGoods.getOriginPlace(),
                filterGoods.getRefrigiratedCondition(),
                lowerPerice, upperPirce, discount, orderByPriceIncrease, orderByDiscount, 10, index);
        sqlSession.close();
        return list;
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
        ArrayList<GoodsInWarehouse> list = goodsInWarehouseMapper.selectByCase(goodsId, customer.getWarehouseId());
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
                tmpSales.setCustomerUserId(customer.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpAmount)));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(tmpGIW.getAmount() - tmpAmount);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
                break;
            } else {
                tmpSales.setAmount(tmpGIW.getAmount());
                tmpSales.setGoodsInWarehouseId(tmpGIW.getIdgoodsInWarehouse());
                tmpSales.setCustomerUserId(customer.getId());
                tmpSales.setIsPaid("N");
                tmpSales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(new BigDecimal(tmpGIW.getAmount())));
                salesMapper.insertSelective(tmpSales);
                tmpGIW.setAmount(0);
                goodsInWarehouseMapper.updateByPrimaryKeySelective(tmpGIW);
            }
        }
        return 0;
    }

<<<<<<< HEAD
    public int buy(ArrayList<Sales> list){
        Order tmpOrder=new Order();
        tmpOrder.setSaleses(list);
        for (Sales sales:list) {
            sales.setIsPaid("Y");
            sales

        }
    }

    public int updateCustomer(Customer customer){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper=sqlSession.getMapper(CustomerMapper.class);
=======
    public int updateCustomer(Customer customer) {
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
>>>>>>> fec15263ef4b5cb1d6ad0c063dade22ec8153801
        mapper.updateByPrimaryKeySelective(customer);
        return 0;
    }

    public ArrayList<Order> getOrder() {
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        Order tmpOrder = new Order();
        tmpOrder.setCustomerUserId(customer.getId());
        ArrayList<Order> list = mapper.selectByCase(tmpOrder);//[add mapper]
        return list;
    }


}
