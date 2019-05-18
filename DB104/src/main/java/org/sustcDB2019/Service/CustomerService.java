package org.sustcDB2019.Service;

import org.sustcDB2019.entity.Customer;
import org.sustcDB2019.entity.Goods;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

import static java.lang.Math.*;

public class CustomerService extends UserService{
    public Customer customer;
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

    public ArrayList<Goods> goodsArrayList() {
        
    }
}
