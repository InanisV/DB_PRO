package org.sustcDB2019.service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.ManagerMapper;
import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Manager;

import java.math.BigDecimal;

public class ManagerService extends UserService{
    Manager Manager=(Manager) super.user;


    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        newManager.setUserName(userName);
        newManager.setPassword(String.format("%d",password.hashCode()));
        newManager.setPhoneNumber(phoneNumber);
        newManager.setWarehouseWarehouseId(warehouseId);
        //add newManager to database
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




//    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
//
//    }
}
