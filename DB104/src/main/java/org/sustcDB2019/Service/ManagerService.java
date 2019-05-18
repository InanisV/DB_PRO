package org.sustcDB2019.Service;

import org.sustcDB2019.entity.Deliverer;
import org.sustcDB2019.entity.Manager;

import java.math.BigDecimal;

public class ManagerService extends UserService{
    Manager currentManager;

    //for main manager
    public int addNewManager(String userName,String password,String phoneNumber,int id,int warehouseId){
        Manager newManager=new Manager(/*String userName,String password,String phoneNumber,int id,int warehouseId*/);
        //add newManager to database
        String str;
        str.hashCode();
        //check if the new manager is in the db here(optional)
        return 0;
    }


    public int addNewWarehouse(String address, int refrigeratedShelfVolume, int ordinaryShelfVolume, BigDecimal warehouseLong,BigDecimal warehouseLati){//BigDecimal or Long or Integer?
        
    }
}
