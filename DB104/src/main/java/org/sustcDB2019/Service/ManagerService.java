package org.sustcDB2019.service;

import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Manager;

import java.math.BigDecimal;

public class ManagerService extends UserService{
    public static Manager manager;
    Manager currentManager;


    public int addNewManager(String userName,String password,String phoneNumber,int warehouseId){
        Manager newManager=new Manager();
        newManager.setUserName(userName);
        newManager.setPassword(String.format("%d",password.hashCode()));
        newManager.setPhoneNumber(phoneNumber);
        newManager.setWarehouseWarehouseId(warehouseId);
        //add newManager to database
<<<<<<< HEAD
        String str;
//        str.hashCode();
=======
>>>>>>> 786d8cb920d873599da131e2d50b427321e2a4a4
        //check if the new manager is in the db here(optional)
        return 0;
    }


//    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
//
//    }
}
