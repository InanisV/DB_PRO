package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.*;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.Date;

public class ManagerService extends UserService{
    public Manager manager=(Manager) super.user;
    Manager Manager=(Manager) super.user;

    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        SqlSession sqlSession = DAOService.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName(userName);
        if(user.getId()!=null){
            // already have name
        }
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        user.setId(managerMapper.selectMaxId()+1);
        user.setPassword(String.format("%d",password.hashCode()));
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        newManager.setUserId(user.getId()+1);
        newManager.setWarehouseWarehouseId(warehouseId);

        user = userMapper.selectByName(userName);
        if(user.getId()!=null){
            // already added
        }
        return 0;
    }

    public int updateManager(Manager manager){
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
        ManagerMapper mapper=sqlSession.getMapper(ManagerMapper.class);
        mapper.updateByPrimaryKeySelective(manager);
        return 0;
    }

    public int purchaseToWarehouse(int purchaseId, int goodsId, int amount, Date productionDate){
        Purchase purchase=new Purchase();
        purchase.setPurchaseId(purchaseId);
        purchase.setGoodsGoodsId(goodsId);
        purchase.setAmount(amount);
        purchase.setProductionDate(productionDate);
        purchase.setWarehouseWarehouseId(Manager.getWarehouseWarehouseId());
        purchase.setDate(currentDate);
        SqlSession sqlSession=DAOService.sqlSessionFactory.openSession();
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




//    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
//
//    }
}
