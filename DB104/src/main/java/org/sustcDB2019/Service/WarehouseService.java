package org.sustcDB2019.Service;

import org.apache.ibatis.session.SqlSession;
import org.sustcDB2019.dao.DaoManager;
import org.sustcDB2019.dao.WarehouseMapper;
import org.sustcDB2019.entity.Warehouse;

import java.util.ArrayList;

public class WarehouseService {
    public static ArrayList<Warehouse> getAllWarehouse(){
        SqlSession sqlSession = DaoManager.sqlSessionFactory.openSession();
        WarehouseMapper mapper = sqlSession.getMapper(WarehouseMapper.class);
        ArrayList<Warehouse> list=mapper.selectAll();
        sqlSession.close();
        return list;
    }
}
