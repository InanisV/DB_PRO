package org.sustcDB2019.Service;

import org.sustcDB2019.dao.WarehouseMapper;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

public class WarehouseService {
    public static ArrayList<Warehouse> getAllWarehouse(){
        return WarehouseMapper.getAllWarehouse();//[add mapper] return all warehouse
    }
}
