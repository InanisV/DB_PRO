package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.CustomerMapper;
import org.sustcDB2019.dao.GoodsInWarehouseMapper;
import org.sustcDB2019.dao.GoodsMapper;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static java.lang.Math.*;

public class CustomerService extends UserService{
    public Customer customer=(Customer) super.user;
    public int updateCustomer(){
        return 0;
    }

    public void updateWarehouse(){
        ArrayList<Warehouse> warehouses=WarehouseService.getAllWarehouse();
        double R=6371.0;
        int minWarehouseId=0;
        double minDistance=0;
        for (int i=0;i<warehouses.size();i++) {
            double phi1 = (90 - customer.getCustomerLati().doubleValue()) * PI/180, phi2 = (90 - warehouses.get(i).getWarehouseLati().doubleValue()) * PI/180;
            double c = sin(phi1) * sin(phi2) * cos((customer.getCustomerLong().doubleValue() - warehouses.get(i).getWarehouseLong().doubleValue()) * PI /180) + cos(phi1) *
                    cos(phi2);
            double d = R * acos(c);
            if (d<minDistance){
                minDistance=d;
                minWarehouseId=i;
            }
        }
        customer.setWarehouseId(minWarehouseId);
    }

//
//    public ArrayList<Goods> goodsArrayList() {
//
//    }

    public ArrayList<Goods> goodsArrayList(int index) {
        SqlSession sqlSession= DAOService.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<Goods> list=goodsInWarehouseMapper.selectWithPages(customer.getWarehouseId(),10,index);
        sqlSession.close();
        return list;
    }

    public int addToCart(int goodsId,int amount){
        Sales sales=new Sales();
        sales.setAmount(amount);
        sales.setGoodsGoodsId(goodsId);
        sales.setWarehouseWarehouseId(customer.getWarehouseId());
        sales.setIsPaid("N");
        sales.setCustomerUserId(customer.getId());
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods=goodsMapper.selectByPrimaryKey(goodsId);
        BigDecimal amountDecimal=new BigDecimal(amount);
        sales.setPayment(goods.getPrice().multiply(goods.getDiscount()).multiply(amountDecimal));
        GoodsInWarehouseMapper goodsInWarehouseMapper = sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<GoodsInWarehouse> list=goodsInWarehouseMapper.selectConditionally(
                String.format("%d",customer.getWarehouseId()),String.format("%d",goods.getGoodsId()),
                null,null,null,null,null,null,null,null,null,
                false,null,null,true);
                );/*[add mapper] with no pages
                ArrayList<Goods> selectConditionallyWithPages(
                String warehouseId,String goodsId, String type, String catagory,
                String name, String brand, String orginPlace,
                String refrigiratedCondition, String lowerPrice, String upperPrice,
                boolean discount, String orderByPriceIncrease, boolean orderByDiscount,boolean orderByExpiredDay);*/
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAmount()>=amount){

            }
        }
        return 0;
    }

    public int updateCustomer(Customer customer){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper=sqlSession.getMapper(CustomerMapper.class);
        mapper.updateByPrimaryKeySelective(customer);
        return 0;
    }



}
