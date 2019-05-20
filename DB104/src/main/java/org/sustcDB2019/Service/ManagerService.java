package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ManagerService extends UserService{
    public Manager manager=(Manager) super.user;


    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName(userName);
        if(user.getId()!=null){
            return -1;
        }
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        user.setId(managerMapper.selectMaxId()+1);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        newManager.setUserId(user.getId()+1);
        newManager.setWarehouseWarehouseId(warehouseId);
        userMapper.insertSelective(user);
        managerMapper.insertSelective(newManager);
        sqlSession.close();
        user = userMapper.selectByName(userName);
        if(user.getId()==null){
            return -1;
        }
        return 0;
    }

    public int updateManager(Manager manager){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        ManagerMapper mapper=sqlSession.getMapper(ManagerMapper.class);
        mapper.updateByPrimaryKeySelective(manager);
        return 0;
    }

    public Goods addNewGoods(Goods newGoods){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper=sqlSession.getMapper(GoodsMapper.class);
        goodsMapper.insertSelective(newGoods);
        return goodsMapper.selectConditionally(newGoods).get(0);
    }

    public int changeGoodsDiscount(int goodsId, double discount){
        BigDecimal discountBD=new BigDecimal(discount);
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        Goods tmpGoods=new Goods();
        tmpGoods.setGoodsId(goodsId);
        tmpGoods.setDiscount(discountBD);
        goodsMapper.updateByPrimaryKeySelective(tmpGoods);
        return 0;
    }


    public int purchaseToWarehouse( int goodsId, int amount, Date productionDate){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsMapper goodsMapper=sqlSession.getMapper(GoodsMapper.class);
        WarehouseMapper warehouseMapper=sqlSession.getMapper(WarehouseMapper.class);
        if (goodsMapper.selectByPrimaryKey(goodsId).getRefrigiratedCondition().equals("Y")){
            if (amount>warehouseMapper.getRefriRestVolume(manager.getWarehouseWarehouseId()))
                return 1;
        }else {
            if (amount>warehouseMapper.getNonRefriRestVolume(manager.getWarehouseWarehouseId()))
                return 1;
        }
        Purchase purchase=new Purchase();
        purchase.setGoodsGoodsId(goodsId);
        purchase.setAmount(amount);
        purchase.setProductionDate(productionDate);
        purchase.setWarehouseWarehouseId(manager.getWarehouseWarehouseId());
        purchase.setDate(currentDate);
        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods=mapper.selectByPrimaryKey(goodsId);
        purchase.setCost(goods.getPrice().multiply(new BigDecimal(amount)));
        PurchaseMapper mapper1=sqlSession.getMapper(PurchaseMapper.class);
        mapper1.insertSelective(purchase);
        return 0;
    }

    public User getUserByName(String name) {
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user =mapper.selectByName(name);
        sqlSession.close();
        return user;
    }

    public Manager getManagerById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        ManagerMapper mapper=sqlSession.getMapper(ManagerMapper.class);
        Manager tmpManager =mapper.selectByPrimaryKey(id);
        sqlSession.close();
        return tmpManager;
    }

    public Customer getCustomerById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CustomerMapper mapper=sqlSession.getMapper(CustomerMapper.class);
        Customer tmpCustomer =mapper.selectByPrimaryKey(id);
        sqlSession.close();
        return tmpCustomer;
    }

    public Deliverer getDelivererById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        DelivererMapper mapper=sqlSession.getMapper(DelivererMapper.class);
        Deliverer tmpDeliverer =mapper.selectByPrimaryKey(id);
        sqlSession.close();
        return tmpDeliverer;
    }

    public Cashier getCashierById(int id){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        CashierMapper mapper=sqlSession.getMapper(CashierMapper.class);
        Cashier tmpCashier =mapper.selectByPrimaryKey(id);
        sqlSession.close();
        return tmpCashier;
    }


    public int[] getRestVolume(int warehouseId){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        WarehouseMapper mapper=sqlSession.getMapper(WarehouseMapper.class);

        int [] volumes=null;
        volumes[1]=mapper.getRefriRestVolume(manager.getWarehouseWarehouseId());
        volumes[0]=mapper.getNonRefriRestVolume(manager.getWarehouseWarehouseId());
        //[add mapper]
        //搞定了  、、@fixed: the four new methods was added
        sqlSession.close();
        return volumes;
    }
    public ArrayList<GoodsInWarehouse> getNearOverdue(){//filter type? category? RefrigiratedCondition? or goods obj?
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        GoodsInWarehouseMapper goodsInWarehouseMapper=sqlSession.getMapper(GoodsInWarehouseMapper.class);
        ArrayList<GoodsInWarehouse> list=null;
        list=goodsInWarehouseMapper.nearlyExpired(manager.getWarehouseWarehouseId());
        //[add mapper] select GoodsInWarehouse whose remaining time = 10% * preserveTime
        //搞定了，在goodsInWarehouse 里
        return list;
    }


    public ArrayList<GoodsWithAmount> getOrderedBySalesVolume(int pageIndex){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        SalesMapper salesMapper=sqlSession.getMapper(SalesMapper.class);
        ArrayList<GoodsWithAmount> list=salesMapper.getSalesVolumeRank(manager.getWarehouseWarehouseId(),20,pageIndex);
        //[add mapper] select count(amount)
        //搞定了，返回值是GoodWithAmount;
        return list;
    }
//-----------------------------------------------------------------------------just some empty method below

    public int getOrderedByProfit(){
        return 0;
    }

//    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
//
//    }
}