package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.GoodsMapper;
import org.sustcDB2019.dao.ManagerMapper;
import org.sustcDB2019.dao.PurchaseMapper;
import org.sustcDB2019.dao.UserMapper;
import org.sustcDB2019.entity.*;

import java.math.BigDecimal;
import java.util.Date;

public class ManagerService extends UserService{
    Manager Manager=(Manager) super.user;


    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        newManager.setUserName(userName);
        newManager.setPassword(String.format("%d",password.hashCode()));
        newManager.setPhoneNumber(phoneNumber);
        newManager.setWarehouseWarehouseId(warehouseId);

        String str;
//        str.hashCode();

        //check if the new manager is in the db here(optional)
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




//    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
//
//    }
}
